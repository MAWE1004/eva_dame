package modelle;

import grafiken.Clock;

class UpdateRequest implements Runnable {
    private Clock clock;
    public UpdateRequest (Clock clock) {
        this.clock = clock;

    } // UpdateRequest
    public void run ( ) {
        clock.update ( );
    } // run
} // UpdateRequest
