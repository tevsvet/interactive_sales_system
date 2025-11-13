package org.program.adapter;

public class PipeOrderFileAdapter extends AbstractOrderFileAdapter {

    public PipeOrderFileAdapter() {
        super("\\|");
    }

    @Override
    public boolean supports(String fileName) {
        return fileName.endsWith(".txt");
    }
}
