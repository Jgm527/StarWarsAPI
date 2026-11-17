# StarWarsAPI

A Java console client for the [SWAPI](https://swapi.dev/) (Star Wars API).
Search films, planets and starships from the terminal, with all their related
entities (characters, residents, pilots...) resolved automatically.

> This is a fork of [maartendekker1998/StarWarsAPI](https://github.com/maartendekker1998/StarWarsAPI)
> with several fixes — see [Changes vs. the original repository](#changes-vs-the-original-repository).

![console summary](console.png)

## Requirements

- Java (JDK 8 or newer) — the jars in `src/libs` (GSON, Apache HttpClient, JUnit) are included in this repository, nothing else to install.
- Optional: [VS Code](https://code.visualstudio.com/) with the *Extension Pack for Java* for one-click running.

## How to run

### Option 1: VS Code (recommended for beginners)

1. Open this folder in VS Code.
2. Open `src/Main.java` and press the **Run** (▶️) button above `main`.
3. The program starts in **interactive mode**: a `command [search] >` prompt appears
   in the terminal. Type commands (see below) and `exit` to quit.

> If it was already open, run **"Java: Clean Java Language Server Workspace"**
> (`Ctrl+Shift+P`) once so it picks up the new `.vscode/settings.json`.

### Option 2: Command line

Compile once (Windows PowerShell, from the project root):

```powershell
javac -encoding UTF-8 -cp "src/libs/*" -d bin src\*.java
```

Then either pass arguments directly:

```powershell
java -cp "bin;src/libs/*" Main planets naboo
java -cp "bin;src/libs/*" Main films hope
java -cp "bin;src/libs/*" Main starships "death star"
```

or run with no arguments to enter the same interactive mode as in VS Code:

```powershell
java -cp "bin;src/libs/*" Main
```

*(On Linux/macOS replace `;` with `:` in the classpath.)*

## Commands

Syntax:

```
[category]                 get all results of a category
[category] [search]        search within a category
```

| Command | Search fields | Example |
|---|---|---|
| `films` | title | `films hope` |
| `planets` | name | `planets naboo` |
| `starships` | name, model | `starships death star` |

- **Category names are case-insensitive** (`Films`, `PLANETS`... work too).
- **Searches may contain spaces and accents** (`starships death star`, `planets padmé`).
- For each result the program prints all its details and resolves every related
  URL with an extra request, e.g. a planet shows its residents and the films it
  appears in. Related entries like R2-D2 can be found by searching their category.
- `help` shows the same manual. Unknown categories report the available ones.

Example interactive session:

```
command [search] > planets naboo
Planet : "Naboo"
Rotation Period : "26"
...
Population : "4500000000"

Residents :
"R2-D2"
"Palpatine"
...

command [search] > exit
```

Note: searching is done server-side by SWAPI; a search term with no matches
prints `Your search didn't get any results`.

## Running the tests

`test/StarWarsAPItest.java` contains JUnit tests (movie count, *A New Hope*
release date, Naboo population, Death Star model, Luke Skywalker lookup).
They require internet access since they hit the live API.

## Changes vs. the original repository

Compared to [maartendekker1998/StarWarsAPI](https://github.com/maartendekker1998/StarWarsAPI)
at commit `0cb937a`:

1. **Interactive mode** (`src/Main.java`) — the original just printed
   `"no parameters, enter help as parameter for manual"` and exited when launched
   without arguments (e.g. the VS Code run button). It now starts a command loop
   where category and search are typed on the console. The original argument-based
   usage still works exactly as before.
2. **Dead API host replaced** (`src/API.java`, `test/StarWarsAPItest.java`) — the
   original pointed at `https://swapi.co/api/`, which shut down in 2020. All
   requests now go to `https://swapi.dev/api/`, the official successor. Without
   this change every request failed with an HTTP error.
3. **URL encoding of the search** (`src/API.java`) — the original inserted the
   search text raw into the URL, so any search containing spaces (or accents)
   threw `IllegalArgumentException: Illegal character in query`. The search is
   now URL-encoded (spaces become `%20`, `padmé` becomes `padm%C3%A9`).
4. **No crash when a request fails** (`src/ArgumentSwitcher.java`) — the original
   dereferenced a `null` JSON object after a failed request and crashed with a
   `NullPointerException`. It now prints a friendly message about the connection.
5. **Case-insensitive categories** (`src/ArgumentSwitcher.java`) — `Films` and
   `PLANETS` are now accepted, not just lowercase input.
6. **VS Code build configuration** (`.vscode/settings.json`) — registers `src`
   and `test` as source folders and the jars in `src/libs` as referenced
   libraries, so the Java extension compiles and runs the project correctly
   out of the box.

## Credits

- Original implementation by [maartendekker1998](https://github.com/maartendekker1998).
- Data from the [SWAPI](https://swapi.dev/) — *this is not affiliated with or endorsed by Lucasfilm/Disney.*
- Libraries bundled in `src/libs`: GSON 2.8.5, Apache HttpClient 4.5.8 / HttpCore 4.4.11, commons-logging 1.2, JUnit 4.13, Hamcrest 1.3.
