package org.example.domain.services.exporters;

public interface Exporter {
    ExportVisitor createVisitor();
}