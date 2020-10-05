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


## 0.6.2-0.3.0-alpha2 / 2020-October-06

- [BREAKING CHANGE] Drop inducer `gossamer.core.inducer/abort` (moved to bract.core)


## 0.6.2-0.3.0-alpha1 / 2020-October-05

- [BREAKING CHANGE] Drop support for Clojure 1.7 (due to Calfpath)
- [TODO - BREAKING CHANGE] Move inducer `gossamer.core.inducer/abort` to bract.core
- [Todo] Ring wrapper for request/response log
  - [Todo] Add DEV traffic logger fns from bract.dev
  - [Todo] Ensure webapp displays request/response logs in dev mode
- Dependency upgrade
  - Bract modules
    - bract.core to 0.6.2
    - bract.cli to 0.6.2-0.1.1
    - bract.ring to 0.6.2-0.2.0
  - Cambium modules
    - SLF4j modules to 1.7.30
    - cambium.core to 1.0.0 (pulls in SLF4j 1.7.30)
    - cambium.codec-cheshire to 1.0.0 (pulls in Cheshire 5.10.0)
    - cambium.logback.json 0.4.4 (pulls in Jackson 2.10.2, same as Cheshire 5.10.0)
  - Calfpath to 0.8.0 (requires Clojure 1.8 or up)
- Documentation
  - Add _cljdoc_ badge
  - Reformat docstring for _cljdoc_
  - Add documentation page


## 0.6.1-0.2.0 / 2019-January-10

- Add resources for webapp workflow
  - `gossamer/core/webapp-context.edn`
  - `gossamer/core/webapp-context.dev.edn`
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
