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
  - [Idea] Security middleware (e.g. anti-forgery, XSS protection, SSL redirect etc. - as module?)
  - [Idea] Session support (as module?)
- Routes
  - [Todo] Web route endpoint logging


## 0.6.1-0.2.0 / 2019-January-??

- Add `gossamer/core/webapp-context.edn` resource for webapp workflow
- Add dependencies `bract.cli` and `bract.ring`
- Add key definitions
  - app-version for `app.version` key
  - host-name for `app.hostname` key
- Add app config entries `gossamer/core/config.edn`
  - `default.app.version`
  - `app.version`
  - `default.app.hostname`
  - `app.hostname`
- Add inducer
  - `gossamer.core.inducer/abort`
- Add utility fns (in `gossamer.core.util` ns)
  - `handle-uncaught-exception`
  - `command-print-version`
  - `start-placeholder-server`


## 0.6.1-0.1.1 / 2019-January-06

- Upgrade Calfpath to version 0.7.1
- Always emit verbose message in `gossamer.core.inducer/calfpath-routes->ring-handler`


## 0.6.1-0.1.0 / 2018-October-10

- Upgrade bract.core to version 0.6.1


## 0.6.0-0.1.0 / 2018-May-16

- Dependencies
  - bract.core 0.6.0
  - cambium.core 0.9.2
  - cambium.logback 0.4.2
  - Calfpath 0.6.0
- Features
  - Structured logging based on Cambium
    - Provided Logback config for text/JSON logs, log rotation/archival, application and metrics logs
    - Direct logs from other logging libraries to SLF4j
    - Default log levels
      - Dev:  app - TRACE, root - INFO
      - Root: app - INFO,  root - WARN
  - Data-driven web routing based on Calfpath
- Inducer
  - `gossamer.core.inducer/calfpath-routes->ring-handler`
  - `gossamer.core.inducer/apply-route-wrappers`
  - `gossamer.core.inducer/log-mdc-codec-init-only`
  - `gossamer.core.inducer/register-logback-deinit`
  - `gossamer.core.inducer/log-mdc-codec-init` (based on `log-mdc-codec-init-only` and `register-logback-deinit`)
- Route wrapper
  - `gossamer.core.route/inner-ping`
- Resources
  - Logging
    - `gossamer/core/logback-included.xml`
    - `gossamer/core/config.logback.edn`
    - `gossamer/core/config.logback.dev.edn`
  - Context: `gossamer/core/context.edn`
  - Config: `gossamer/core/config.edn`
