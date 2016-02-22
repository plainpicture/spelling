
# Spelling

Multi-language, Multi word, utf-8 spelling correction server using a levenshtein automaton and a Trie.
Written in java, requires java >= 1.8. Several optimizations like e.g. pruning the search trie via already found
distances and frequencies provide single millisecond performance for single keyword corrections.

Compiliation:

```
$ mvn clean install
```

Run:

```
$ java -jar target/spelling-0.0.1-jar-with-dependencies.jar /path/to/dictionary.txt
```

The dictionary file is a simple tab separated list:

```
en  keyword 1 29383
en  keyword 2 28929
...
```

containing three columns per line. First column is an arbitrary locale (language identifier),
the second column is the keyword or phrase and the third column is a frequency or score value.
If there are multiple matches with the same distance, the one with a higher frequency wins.

The server listens on TCP port 12182 and uses a simple JSON based protocol.

Request:

```json
{"query":"keywrd x","locale":"en"}\n
```

Response:

```json
{"query":keyword 1","distance":2,"took":6}
```

`took` tells you how long the response took.

Currently, certain defaults apply:

* tokens < 4 characters: won't be corrected
* tokens = 4 characters: a maximum edit distance of 1 is used
* tokens > 4 characters: a maximum edit distance of 2 is used

