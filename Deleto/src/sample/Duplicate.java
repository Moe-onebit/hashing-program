package sample;

import java.io.File;

class Duplicate {
    File dup;
    boolean toDelete;

    Duplicate(File f) {
        dup = f;
        toDelete = false;
    }
}
