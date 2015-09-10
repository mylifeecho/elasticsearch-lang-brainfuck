# Brainfuck lang Plugin for Elasticsearch

The Brainfuck language plugin allows to have `brainfuck` as the language of scripts to execute.

In order to install the plugin, simply run:

```bash
gradle clean buildPluginZip
plugin --install lang-brainfuck \
       --url file:build/distributions/elasticsearch-lang-brainfuck-0.0.1-plugin.zip
```

#User Guide

## Using python with aggregations

Let's say you want to use calculate key using brainfuck. Here is a way of doing it:

```sh
curl -XDELETE "http://localhost:9200/test"

curl -XPUT "http://localhost:9200/test/doc/1" -d '{
  "some_property": 1
}'

curl -XPOST http://localhost:9200/_search -d '
{
  "aggs": {
    "script": {
      "terms": {
        "lang": "brainfuck",
        "script": "++++++++++[>+++++++>++++++++++>+++<<<-]>++.>+.+++++++..+++.>++.<<+++++++++++++++.>.+++.------.--------.>+."}}}}'
```

gives

```json
{
  // ...
  "aggregations": {
    "script": {
      "doc_count_error_upper_bound": 0,
      "sum_other_doc_count": 0,
      "buckets": [{
        "key": "Hello World!", // <--- resutl of script evaluation
        "doc_count": 1 }]}}}
```
