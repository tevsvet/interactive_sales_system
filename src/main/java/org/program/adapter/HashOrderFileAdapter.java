package org.program.adapter;

public class HashOrderFileAdapter extends AbstractOrderFileAdapter {

    public HashOrderFileAdapter() {
        super("#");
    }

    @Override
    public boolean supports(String fileName) {
        return !fileName.contains(".");
    }
}
