package org.shimomoto.yakety.validation;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.shimomoto.yakety.validation.api.SetViolation;

import java.util.Set;

@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
@Getter
public class BaseSetIdViolation<I> implements SetViolation<I> {
	@NonNull
	private final Set<I> setIds;
	@NonNull
	private final String violationCode;
}
