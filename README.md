# Mocha Doom

![Top Language](https://img.shields.io/github/languages/top/axdoomer/mochadoom.svg?style=flat)
![Code Size](https://img.shields.io/github/languages/code-size/axdoomer/mochadoom.svg?style=flat)
![License](https://img.shields.io/github/license/axdoomer/mochadoom.svg?style=flat&logo=gnu)

Mocha Doom is a pure Java Doom source port. Most of the hard work of porting Doom to Java has already been done, thanks to Velktron (Maes), but he has stopped working on it in 2013. Although the port is almost complete, some work remains to do, most importantly the network code for the multiplayer is missing. Features like support for the Boom format would also be great. I have decided to continue the development in my free time and fix some bugs.

# PRO250 Modifications

This fork of the project adds a simple configuration window before the engine launches, that allows the user to select a game and toggle autorun.
The games are read from what ever folder is specified in the `gamesdir` item in `mochadoom.cfg`.
The last game played is persisted as the `lastgame` item in `mochadoom.cfg`.

# How to run

1. Make sure you have a valid install of Java 11
2. Place valid game `.wad` files in the games directory. (Freedoom works for this and can be found at https://freedoom.github.io/download.html download the phase1+2 zip and place the files ending in `.wad` in the games folder)
3. Open the src directory in IntelliJ IDEA
4. Change the project SDK if necessary
5. Add a new Application Run Configuration called Engine and set the main class to mochadoom.Engine
6. MAKE ABSOLUTELY SURE THE PROJECT IS USING JAVA 11
7. Run the Engine Configuration

~~1. Open the project with Eclipse or NetBeans~~
~~2. Delete every file that has errors (if any)~~
~~3. Build and run the project~~

## Advanced users

On Linux, two different scripts can be used.

1. `build-and-run.sh` which will build Mocha Doom and run it. You can use it as such: `./build-and-run.sh -iwad ~/DOOM2.WAD`. This is the preferred way to quickly test changes for developers.
2. `build-jar.sh` which will build a JAR file. You can then run the JAR file as such: `java -jar mochadoom.jar -iwad ~/DOOM2.WAD`. This is the preferred way for distributing a Mocha Doom executable.

# License

Mocha Doom contains work from many contributors. Here are the main contributors, but it's no limited to this list. Others are listed in the copyright headers of the files where they own copyright.

- Copyright (C) 1993-1996  [id Software, Inc.](http://www.idsoftware.com/)
- Copyright (C) 2010-2013  [Victor Epitropou](https://sourceforge.net/projects/mochadoom/)
- Copyright (C) 2016-2017  [Alexandre-Xavier Labonté-Lamoureux](https://github.com/AXDOOMER/)
- Copyright (C) 2017  [Good Sign](https://github.com/GoodSign2017)

Mocha Doom is distributed under the [GNU GPLv3](https://www.gnu.org/licenses/gpl-3.0.en.html).

# Rip and Tear!

Mocha Doom in action:
![so_much_blood](https://cloud.githubusercontent.com/assets/6194072/18658610/94a326c2-7ed2-11e6-98af-4ed4c8b28510.png)
