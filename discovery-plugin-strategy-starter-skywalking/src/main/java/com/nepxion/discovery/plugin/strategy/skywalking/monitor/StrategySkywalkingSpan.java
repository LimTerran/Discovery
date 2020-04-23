package com.nepxion.discovery.plugin.strategy.skywalking.monitor;

/**
 * <p>Title: Nepxion Discovery</p>
 * <p>Description: Nepxion Discovery</p>
 * <p>Copyright: Copyright (c) 2017-2050</p>
 * <p>Company: Nepxion</p>
 * @author Haojun Ren
 * @version 1.0
 */

import io.opentracing.Span;
import io.opentracing.SpanContext;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class StrategySkywalkingSpan implements Span {
    private static final Logger LOG = LoggerFactory.getLogger(StrategySkywalkingSpan.class);

    private Span span;
    private String traceId;
    private String spanId;

    public StrategySkywalkingSpan(Span span) {
        this.span = span;
        this.traceId = createTraceId();
        this.spanId = createSpanId();
    }

    @Override
    public Span setOperationName(String operationName) {
        return span.setOperationName(operationName);
    }

    @Override
    public Span log(long timestampMicroseconds, Map<String, ?> fields) {
        return span.log(timestampMicroseconds, fields);
    }

    @Override
    public void finish(long finishMicros) {
        span.finish(finishMicros);
    }

    @Override
    public Span log(long timestampMicroseconds, String event) {
        return span.log(timestampMicroseconds, event);
    }

    @Override
    public void finish() {
        span.finish();
    }

    @Override
    public SpanContext context() {
        return span.context();
    }

    @Override
    public Span setTag(String key, String value) {
        return span.setTag(key, value);
    }

    @Override
    public Span setTag(String key, boolean value) {
        return span.setTag(key, value);
    }

    @Override
    public Span setTag(String key, Number value) {
        return span.setTag(key, value);
    }

    @Override
    public Span log(Map<String, ?> fields) {
        return span.log(fields);
    }

    @Override
    public Span log(String event) {
        return span.log(event);
    }

    @Override
    public Span setBaggageItem(String key, String value) {
        return span.setBaggageItem(key, value);
    }

    @Override
    public String getBaggageItem(String key) {
        return span.getBaggageItem(key);
    }

    @Deprecated
    @Override
    public Span log(String eventName, Object payload) {
        return span.log(eventName, payload);
    }

    @Deprecated
    @Override
    public Span log(long timestampMicroseconds, String eventName, Object payload) {
        return span.log(timestampMicroseconds, eventName, payload);
    }

    public String toTraceId() {
        return traceId;
    }

    public String toSpanId() {
        return spanId;
    }

    private String createTraceId() {
        try {
            return StrategySkywalkingResolver.createTraceId();
        } catch (Exception e) {
            LOG.error("Create TraceId failed", e);

            return null;
        }
    }

    private String createSpanId() {
        try {
            return StrategySkywalkingResolver.createSpanId();
        } catch (Exception e) {
            LOG.error("Create SpanId failed", e);

            return null;
        }
    }
}