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
  - [Todo] Use Calfpath 0.6.0


## [WIP] 0.6.0-alpha2 / 2018-March-??

- Use Calfpath 0.6.0
  - [Todo] Use the GA version
- Use config for compiling Calfpath routes
- [Todo] Export property `"logback.app.base.package"`
- [Todo] Use above property as base package name
- [Todo] Add command to print version



## 0.6.0-alpha1 / 2018-March-14

- Use bract.core 0.6.0-alpha1
- Features
  - Structured logging based on Cambium
  - Data-driven web routing based on Calfpath
- Inducer
  - `gossamer.core.inducer/log-mdc-codec-init`
  - `gossamer.core.inducer/calfpath-routes->ring-handler`
  - `gossamer.core.inducer/apply-route-wrappers`
- Route wrapper
  - `gossamer.core.route/inner-ping`
