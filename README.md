
# Spelling

Multi-language, Multi word, utf-8 spelling correction and suggestion server using a levenshtein automaton and a Trie.
Written in java. Using several optimizations like e.g. pruning the search trie via already found
distances.

Compiliation:

```
$ mvn clean install assembly:single
```

Run:

```
$ java -jar target/spelling-0.0.1-jar-with-dependencies.jar /path/to/dictionary1.txt /path/to/dictionary2.txt ...
```

The dictionary file is a simple tab separated list:

```
en  keyword 1 29383
en  keyword 2 28929
...
```

containing three columns per line. First column is an arbitrary locale (language identifier),
the second column is the keyword or phrase and the third column is a frequency or score value.
If there are multiple matches with the same distance, higher phonetic similarity (de: Kölner Phonetik,
en: Double Metaphone, fr: Custom) wins. Otherwise, the one with a higher frequency wins.
Phrases generally get better score (distance / number of words).

The server listens on port 8888 and uses a simple JSON based protocol.

## Correction

Request:

curl -X GET http://127.0.0.1:8888/correct?locale=en&query=keywrd+x

Response:

```json
{
  "query": "keyword 1",
  "distance": 2,
  "took": 6
}
```

`took` tells you how long the response took.

## Suggestion

curl -X GET http://127.0.0.1:8888/suggest?locale=en&query=kyeword+sn

Response:

```json
{
  "took": 4,
  "suggestions": [
    { "query": "keyword snow", "frequency": 2919 },
    { "query": "keyword snowfall", "frequency":2140},
    "..."
  ]
}
```

Currently, certain defaults apply:

* tokens < 4 characters: won't be corrected
* tokens = 4 characters: a maximum edit distance of 1 is used
* tokens > 5 characters: a maximum edit distance of 2 is used

