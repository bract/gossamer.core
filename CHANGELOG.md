# gossamer.core Change Log

## Todo

- Espejito support
  - [Todo] Inducer to instrument vars
  - [Todo] Ring wrapper to start/report latency instrumentation
  - [Todo] Routes wrapper to add threshold based reporting
- Inducers
  - [Todo] Trace logging instrumentation support
- Ring wrapers
  - [Todo] Distributed trace (requires logging integration)
  - [Todo] SSE streaming (requires ring-sse-middleware dependency)
  - [Todo] Ring metrics  (requires ring-metrics dependency)
  - [Todo] Log level override (requires logback dependency)
  - [Todo] Ring-metrics
  - [Todo] SSE middleware
- Routes
  - [Todo] Endpoint logging


## [WIP] 0.6.0 / 2018-March-??

- Features
  - Structured logging based on Cambium
  - Data-driven web routing based on Calfpath
- Inducer
  - log-mdc-codec-init
  - calfpath-routes->ring-handler
  - apply-route-wrappers
- Route wrapper
  - inner-ping
