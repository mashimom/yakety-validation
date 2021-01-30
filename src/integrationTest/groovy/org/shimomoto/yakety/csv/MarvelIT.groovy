package org.shimomoto.yakety.csv

import java.util.stream.Collectors

import static configuration.marvel.MarvelUniverseColumns.*
import static configuration.marvel.MarvelUniverseIndexColumn.INDEX
import configuration.marvel.api.IMarvelUniverseColumn
import spock.lang.Specification

class MarvelIT extends Specification {

	def "Parse a small sample using code defined columns"() {
		given:
		IMarvelUniverseColumn index = INDEX
		IMarvelUniverseColumn[] cols = values() as IMarvelUniverseColumn[]
		def config = FileFormatConfiguration.builder().trim(true).build()
		and: 'a parser'
		def parser = CsvParserFactory.toRowIndexedTextMap(config, index, cols.toList())
		and: 'the simple tiny content'
		def content = '''\
								Title,                              Release date, Phase, Film/TV, In-universe year
								Iron Man,                           2008-05-02,   1,     Film,    2008
								The Incredible Hulk,                2008-06-13,   1,     Film,    2009
								Iron Man 2,                         2010-04-30,   1,     Film,    2009
								Thor,                               2011-04-27,   1,     Film,    2009
								Captain America: The First Avenger, 2011-07-29,   1,     Film,    1942-1945
								The Avengers,                       2012-04-26,   1,     Film,    2010
								'''.stripIndent()
		when:
		List<Map<IMarvelUniverseColumn, String>> result = parser.parse(content).collect(Collectors.toList())

		then:
		result.size() == 6
		result[0] == [(INDEX): '1', (TITLE): 'Iron Man', (RELEASE_DATE): '2008-05-02', (PHASE): '1', (FILM_TV): 'Film', (IN_UNIVERSE_YEAR): '2008']
		result[1] == [(INDEX): '2', (TITLE): 'The Incredible Hulk', (RELEASE_DATE): '2008-06-13', (PHASE): '1', (FILM_TV): 'Film', (IN_UNIVERSE_YEAR): '2009']
		result[2] == [(INDEX): '3', (TITLE): 'Iron Man 2', (RELEASE_DATE): '2010-04-30', (PHASE): '1', (FILM_TV): 'Film', (IN_UNIVERSE_YEAR): '2009']
		result[3] == [(INDEX): '4', (TITLE): 'Thor', (RELEASE_DATE): '2011-04-27', (PHASE): '1', (FILM_TV): 'Film', (IN_UNIVERSE_YEAR): '2009']
		result[4] == [(INDEX): '5', (TITLE): 'Captain America: The First Avenger', (RELEASE_DATE): '2011-07-29', (PHASE): '1', (FILM_TV): 'Film', (IN_UNIVERSE_YEAR): '1942-1945']
		result[5] == [(INDEX): '6', (TITLE): 'The Avengers', (RELEASE_DATE): '2012-04-26', (PHASE): '1', (FILM_TV): 'Film', (IN_UNIVERSE_YEAR): '2010']
	}

	def "Parse inputstream code defined columns"() {
		given:
		IMarvelUniverseColumn index = INDEX
		IMarvelUniverseColumn[] cols = values() as IMarvelUniverseColumn[]
		def config = FileFormatConfiguration.builder().trim(true).build()
		and: 'a parser'
		def parser = CsvParserFactory.toRowIndexedTextMap(config, index, cols.toList())
		and: 'content as inputstream'
		BufferedInputStream content = new BufferedInputStream(getClass().getResourceAsStream('mcu.csv'))

		when:
		List<Map<IMarvelUniverseColumn, String>> result = parser.parse(content).collect(Collectors.toList())

		then:
		result.size() == 64
		result.collect { it[INDEX] }.toSet().size() == 64
	}

	def "Parse inputstream with textual defined columns"() {
		given:
		def cols = ['Title', 'Release date', 'Phase', 'Film/TV', 'In-universe year']
		def config = FileFormatConfiguration.builder().trim(true).build()
		and: 'a parser'
		def parser = CsvParserFactory.toRowIndexedTextMap(config, '#', cols)
		and: 'content as inputstream'
		BufferedInputStream content = new BufferedInputStream(getClass().getResourceAsStream('mcu.csv'))

		when:
		List<Map<String, String>> result = parser.parse(content).collect(Collectors.toList())

		then: 'count rows as header is discarded'
		result.size() == 64
		and: 'all indexes must be distinct'
		result.collect { it['#'] }.toSet().size() == 64
		and: 'last record is ok'
		result.last()['#'] == '64'
		result.last()['Title'] == 'Untitled Hawkeye series'
		result.last()['Release date'] == ''
		result.last()['Phase'] == ''
		result.last()['Film/TV'] == 'TV (Disney+)'
		result.last()['In-universe year'] == ''
	}

	def "Parse inputstream without defining columns"() {
		given:
		def config = FileFormatConfiguration.builder().trim(true).build()
		and: 'a parser'
		def parser = CsvParserFactory.toText(config)
		and: 'content as inputstream'
		BufferedInputStream content = new BufferedInputStream(getClass().getResourceAsStream('mcu.csv'))

		when:
		List<List<String>> result = parser.parse(content).collect(Collectors.toList())

		then: 'count of header and rows is correct'
		result.size() == 65
		and: 'header is the first "record"'
		result.first() == ['Title', 'Release date', 'Phase', 'Film/TV', 'In-universe year']
		and: 'last record is ok'
		result.last()[0] == 'Untitled Hawkeye series'
		result.last()[1] == ''
		result.last()[2] == ''
		result.last()[3] == 'TV (Disney+)'
		result.last()[4] == ''
	}
}
