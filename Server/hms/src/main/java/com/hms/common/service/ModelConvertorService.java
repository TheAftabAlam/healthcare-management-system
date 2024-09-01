package com.hms.common.service;

public interface ModelConvertorService {
	<D, T> D map(final T entity, Class<D> outClass);
}