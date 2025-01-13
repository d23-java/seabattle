# Sea Battle

Sea Battle is a game for two players. The game is played on four grids, two for each player. The grids are typically square – usually 10×10 – and the individual squares in the grid are identified by letter and number. On one grid the player arranges ships and records the shots by the opponent. On the other grid the player records their own shots.

The project was done by [Trần Hải Long](https://github.com/team3hailong), in the course of Object Oriented Programming at [ProPTIT](https://proptit.com/).


## Tech Stack

- [Java](https://www.java.com/en/) 

## Software Design (UML)
### Class Diagram
- ![](https://www.mermaidchart.com/raw/ab2383d5-c560-43b7-a340-e5267c9fe866?theme=light&version=v0.1&format=svg)
 
### Activity Diagram
- ![](https://www.mermaidchart.com/raw/46e8e0ea-361b-4017-bbf0-bfc7cfa1cfd3?theme=light&version=v0.1&format=svg)

## Demo Video

[![[ProPTIT] OOP Course - SeaBattle](https://i.ytimg.com/an_webp/kl95eOPqVwc/mqdefault_6s.webp?du=3000&sqp=CNzsuLsG&rs=AOn4CLDu6OuV-kCNvxhgcTgt4ZCorxNdNQ)](https://www.youtube.com/watch?v=kl95eOPqVwc "[ProPTIT] OOP Course - SeaBattle")

## Demo Image
![](./Demo/seabattle-1.png)
![](./Demo/seabattle-2.png)


## Features
- Players can play against the computer
- Players can play against another player
- Players can choose the game mode (single-player - or multiplayer)
- Players can select the board size
- Display a leaderboard to track scores
- Use power-ups such as Bomb, Shield, and Light
- Option to enable or disable sound
- Save and continue game progress
- Display detailed game rules
- Turn-based interactive game mechanics
- Automatically or manually place ships

## Installation

- Clone the repo
- Open the project in your IDE
- Run the project

## Usage

- Project Structure

```bash

├── src
│   ├── main
│   │   ├── java
│   │   │   ├── enums
│   │   │   │   ├── CellStatus.java
│   │   │   │   ├── FireResult.java
│   │   │   │   ├── GameStatus.java
│   │   │   │   ├── Orientation.java
│   │   │   │   └── ShipType.java
│   │   │   ├── gamemanager
│   │   │   │   ├── BotFunction.java
│   │   │   │   ├── GameFunction.java
│   │   │   │   ├── PlayerFunction.java
│   │   │   │   ├── ShipPlacement.java
│   │   │   │   └── BoardController.java
│   │   │   ├── game
│   │   │   │   ├── Board.java
│   │   │   │   ├── Cell.java
│   │   │   │   ├── Game.java
│   │   │   │   ├── GameState.java
│   │   │   │   ├── Leaderboard.java
│   │   │   │   ├── Main.java
│   │   │   │   ├── Menu.java
│   │   │   │   ├── Player.java
│   │   │   │   ├── PlayerRecord.java
│   │   │   │   ├── Ship.java
│   │   │   │   └── Sound.java
│   │   │   └── items
│   │   │       ├── Bomb.java
│   │   │       ├── Item.java
│   │   │       ├── Shield.java
│   │   │       └── Light.java


```

## Contributing

Pull requests are welcome. For major changes, please open an issue first
to discuss what you would like to change.

Please make sure to update tests as appropriate.

## License

[MIT](https://choosealicense.com/licenses/mit/)