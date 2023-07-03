package com.application.document.task;

import java.util.function.BiFunction;

/**
 * Created by Semih, 2.07.2023
 */
public interface Mappers<T, R, S> extends BiFunction<T, R, S> {
}
