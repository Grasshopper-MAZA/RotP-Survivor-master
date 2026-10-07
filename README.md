# Survivor Stand Addon

A Ripples of the Past addon that adds the Survivor Stand from JoJo's Bizarre Adventure Part 6: Stone Ocean.

Based on the RotP Example Addon template by StandoByte.

## About Survivor

Survivor is the Stand of Guccio. It is an automatic, remote Stand with no physical form of its own. It channels a weak electrical current through wet surfaces, stimulating the limbic system of anyone standing on them and amplifying their aggression to the point where they attack anyone nearby - friend or foe.

## Features

- Summon the Survivor Stand with no visible body, only its effect on the environment.
- Automatic activation through water and waterlogged blocks.
- After soaking, targets gain 30 seconds of "Wet" status.
- While wet, anger gradually builds up over 5 seconds.
- Once anger reaches 100%, targets begin attacking anything nearby indiscriminately.
- Only hostile, neutral and passive mobs are affected. The Stand user is immune.
- Custom sounds and particle effects.

## Setting up the modding workspace

To set up the project:

- Install an IDE - IntelliJ IDEA Community Edition or Eclipse.
- Install JDK 8 - needed for Minecraft 1.16.5.
  - Windows x64 download link: [https://javadl.oracle.com/webapps/download/GetFile/1.8.0_311-b11/4d5417147a92418ea8b615e228bb6935/windows-i586/jdk-8u311-windows-x64.exe](https://javadl.oracle.com/webapps/download/GetFile/1.8.0_311-b11/4d5417147a92418ea8b615e228bb6935/windows-i586/jdk-8u311-windows-x64.exe)
- Open the project in your IDE (Eclipse users: import as a Gradle Project).
- If you get an error after opening the project, change Gradle's Java Home path to where Java 8 is installed (on Windows, `C:\Program Files\Java\jdk1.8.0_311`) in the project settings.

## License

[GNU GPL v3.0](https://choosealicense.com/licenses/gpl-3.0/)