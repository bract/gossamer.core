# Introduction to gossamer.core

Gossamer is a micro web framework module for Clojure. The gossamer.core module combines the following:

- [Bract](https://bract.github.io/) modules with following dependencies
  - Clojure [tools.cli](https://github.com/clojure/tools.cli)
  - Clojure [tools.namespace](https://github.com/clojure/tools.namespace) in DEV mode for REPL workflow
  - [Ring](https://github.com/ring-clojure/ring) for serving web resources
- [Cambium](https://cambium-clojure.github.io/) (for structured logging)
  - Clojure [tools.logging](https://github.com/clojure/tools.logging)
  - [SLF4j](http://www.slf4j.org/) logging facade
  - [Logback](http://logback.qos.ch/) logging implementation
  - [Cheshire](https://github.com/dakrone/cheshire) for JSON codec
- [Calfpath](https://github.com/kumarshantanu/calfpath) (for web routing)


## Usage

Since _gossamer.core_ builds upon _bract.core_ and other Bract modules, you may check the provided context and
config files for the keys being used. A quickstart usage is as follows:

```shell
lein new gossamer myapp
cd myapp
```

Inspect the generated web application, starting with the `README.md` file.


## Context keys

| Context key               | Value type        | Description                                                      |
|---------------------------|-------------------|------------------------------------------------------------------|
|`:gossamer/calfpath-routes`| vector of maps    | Calfpath route maps                                              |
|`:gossamer/route-wrappers` | vector of fn/FQFN | Route wrapper functions `(fn [routes context & more]) -> routes` |


## Config keys

| Config key                          | Value type       | Description                              |
|-------------------------------------|------------------|------------------------------------------|
|`"gossamer.routes.compile.options"`  | map              | Compile options for Calfpath routes, see [calfpath.route/compile-routes](https://cljdoc.org/d/calfpath/calfpath/0.8.0-alpha4/api/calfpath.route#compile-routes)      |
|`"gossamer.inner.ping.enabled"`      | boolean          | Add inner ping route for latency checks? |
|`"gossamer.inner.ping.endpoint.uris"`| vector of string | Vector of inner ping endpoint URIs       |
|`"gossamer.inner.ping.endpoint.body"`| string           | String body for inner ping response      |
|`"gossamer.inner.ping.content.type"` | string           | Content type for inner ping body         |
|`"app.version"`                      | string           | Application version                      |
|`"app.hostname"`                     | string           | Application hostname                     |


## Provided default context/config

| File name                                                                 | Available to applications as         | Description           |
|---------------------------------------------------------------------------|--------------------------------------|-----------------------|
|[logback-included.xml](../resources/gossamer/core/logback-included.xml)    |`gossamer/core/logback-included.xml`  |base Logback config    |
|[context.edn](../resources/gossamer/core/context.edn)                      |`gossamer/core/context.edn`           |default inducer context|
|[webapp-context.edn](../resources/gossamer/core/webapp-context.edn)        |`gossamer/core/webapp-context.edn`    |standard webapp context|
|[webapp-context.dev.edn](../resources/gossamer/core/webapp-context.dev.edn)|`gossamer/core/webapp-context.dev.edn`|DEV mode webapp context|
|[config.edn](../resources/gossamer/core/config.edn)                        |`gossamer/core/config.edn`            |default Gossamer config|
|[config.logback.edn](../resources/gossamer/core/config.logback.edn)        |`gossamer/core/config.logback.edn`    |Logback standard config|
|[config.logback.dev.edn](../resources/gossamer/core/config.logback.dev.edn)|`gossamer/core/config.logback.dev.edn`|Logback DEV mode config|
