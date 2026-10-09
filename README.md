# 🧠 Trivia Quiz App

A single-page trivia quiz desktop app built with JavaFX. Questions come live from the [Open Trivia DB](https://opentdb.com) API.

![Java](https://img.shields.io/badge/Java-17+-ED8B00?logo=openjdk&logoColor=white)
![JavaFX](https://img.shields.io/badge/JavaFX-4285F4?logo=java&logoColor=white)
![Maven](https://img.shields.io/badge/Maven-C71A36?logo=apachemaven&logoColor=white)

## Screenshots

[Add 2-3 screenshots: setup screen, a question, the final score]

## Features

- Choose the **number of questions**, **category**, **difficulty**, and **question type**
- Fetches questions from the Open Trivia DB API
- Shows questions one at a time
- Indicates whether each answer is correct or incorrect
- Shows your final score at the end

## Requirements

- Java 17 or newer
- Maven
- Internet connection (to fetch questions)
- *(Optional)* [Scene Builder](https://gluonhq.com/products/scene-builder/) to edit the FXML layouts

## Getting started

```bash
git clone https://github.com/unmoha/Trivia-quiz-app.git
cd Trivia-quiz-app
mvn clean javafx:run
```

## How it works

1. Pick your quiz settings (number of questions, category, difficulty, type).
2. The app requests matching questions from the Open Trivia DB API.
3. Answer each question and see right or wrong right away.
4. View your final score.

## Tech stack

| Layer | Tools |
|---|---|
| Language | Java 17+ |
| UI | JavaFX (FXML) |
| Build | Maven |
| Data source | Open Trivia DB API |

## Roadmap

- [ ] Timer for each question
- [ ] Score history
- [ ] Sound effects and animations

## Credits

Questions provided by [Open Trivia DB](https://opentdb.com) under its terms of use.

## Author

**Anwar Mohammed** · [an8702299@gmail.com](mailto:an8702299@gmail.com) · [GitHub](https://github.com/unmoha)
