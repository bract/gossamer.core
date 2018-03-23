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


## [WIP] 0.6.0-alpha3 / 2018-March-??

- Update dependencies
  - bract.core 0.6.0-alpha3
  - cambium.core 0.9.2
  - cambium.logback 0.4.2
- Update default log levels in provided Logback config
  - Dev:  app - TRACE, root - INFO
  - Root: app - INFO,  root - WARN
- Default config for Cambium caller-metadata
- Inducers
  - Add `log-mdc-codec-init-only`
  - Add `register-logback-deinit`
  - Refactor `log-mdc-codec-init` to use above two


## 0.6.0-alpha2 / 2018-March-20

- Use Calfpath 0.6.0-alpha2
- Use config for compiling Calfpath routes
- Use app base package for provided logging config
  - Require variable `logback.app.base.package` in `logback-included.xml`
  - Export property `"logback.app.base.package"` in `config.logback.edn`


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
