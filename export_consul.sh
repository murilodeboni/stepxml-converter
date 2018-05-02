#!/bin/bash

source /etc/environment

consul kv put $APPLICATION_NAME/consul/service/name "stepxml-converter"
consul kv put $APPLICATION_NAME/consul/service/port "8080"
consul kv put $APPLICATION_NAME/consul/service/check/name "Services"
consul kv put $APPLICATION_NAME/consul/service/check/http "http://localhost:8080/health"
consul kv put $APPLICATION_NAME/consul/service/check/interval "10s"

consul kv put $APPLICATION_NAME/filebeat/name "$APPLICATION_NAME"
consul kv put $APPLICATION_NAME/filebeat/tags "'step','xml','stibo', 'systems', 'converter', 'v1'"
consul kv put $APPLICATION_NAME/filebeat/prospectors/paths "/app/logs/service.log"

consul kv put $APPLICATION_NAME/logging/file "./logs/service.log"
consul kv put $APPLICATION_NAME/logging/level/root "info"
consul kv put $APPLICATION_NAME/logging/level/org/springframework "error"
consul kv put $APPLICATION_NAME/logging/level/com/step "debug"
consul kv put $APPLICATION_NAME/logging/pattern/file "\"[%d{ISO8601}] %-5level %-40.40c{1.} : %m%ex%n\""

consul kv put $APPLICATION_NAME/management/health/diskspace.enabled "false"

consul kv put $APPLICATION_NAME/server/port "9090"

consul kv put $APPLICATION_NAME/service/name "stepxml-converter"
consul kv put $APPLICATION_NAME/service/version "0.0.1"
consul kv put $APPLICATION_NAME/service/stepxml/path "/Users/igce/Desenvolvimento"
consul kv put $APPLICATION_NAME/service/stepxml/extension ".xml"

consul kv put $APPLICATION_NAME/log/error/stepxmlReader "There was an error reading STEP Xml"