package com.nivedita.realestate.util.rx;

import io.reactivex.Scheduler;

/**
 * An interface which defines basic scheduling services
 */

public interface SchedulerProvider {

    Scheduler ui();

    Scheduler computation();

    Scheduler io();

}
