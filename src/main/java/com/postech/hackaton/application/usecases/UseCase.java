package com.postech.hackaton.application.usecases;

public interface UseCase<I, O> {
    O execute(I request);
}
