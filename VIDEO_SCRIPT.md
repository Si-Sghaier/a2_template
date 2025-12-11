# Video Submission Script
## Bakery Simulation - Assignment 2

**Target Duration:** Under 7 minutes  
**Total Marks:** 70 marks (out of 100)

---

## Pre-Recording Setup

- [ ] Ensure terminal/command prompt is ready
- [ ] Have IDE/editor open with code visible
- [ ] Test that `run.sh` works and simulation starts
- [ ] Have student card ready
- [ ] Position camera to show face and student card clearly

---

## SCRIPT OUTLINE

### Section 1: Introduction & Identity Verification (30 seconds)
**Action:** Show face and student card to camera  
**Say:** "Hello, my name is [Your Name], student ID [Your ID]. This is my submission for Assignment 2 - the Bakery Simulation."

---

### Section 2: Running the Simulation - Basic Commands (2 minutes)
**Action:** Switch to terminal/command prompt  
**Action:** Run `./run.sh` or equivalent to start simulation

#### 2.1 Initial State Check
**Action:** Type `info resources`  
**Say:** "Let me check my starting resources. I begin with 1000 CREDITS."

**Action:** Type `info entities`  
**Say:** "I have no entities built yet."

#### 2.2 Building Producers
**Action:** Type `build wheatfarm`  
**Say:** "I'll build a wheat farm, which costs 100 CREDITS and produces 10 WHEAT per tick."

**Action:** Type `build sugarfarm`  
**Say:** "Next, a sugar farm for 100 CREDITS."

**Action:** Type `build chickencoop`  
**Say:** "A chicken coop for 150 CREDITS, producing eggs."

**Action:** Type `build dairyfarm`  
**Say:** "And a dairy farm for 150 CREDITS, producing milk."

**Action:** Type `info resources`  
**Say:** "I've spent 500 CREDITS, leaving me with 500 remaining."

#### 2.3 Advancing Time
**Action:** Type `tick` (repeat 2-3 times)  
**Say:** "Each tick advances the simulation. My producers create resources simultaneously."

**Action:** Type `info resources`  
**Say:** "You can see resources accumulating - wheat, sugar, eggs, and milk."

#### 2.4 Building Converters
**Action:** Type `build flourmill`  
**Say:** "I'll build a flour mill for 200 CREDITS. It converts 10 WHEAT into 5 FLOUR."

**Action:** Type `build oven`  
**Say:** "An oven for 300 CREDITS. It requires flour, sugar, eggs, and milk to produce cakes."

**Action:** Type `info resources`  
**Say:** "I've now spent all 1000 starting credits."

#### 2.5 Using Cheat Command
**Action:** Type `cheat`  
**Say:** "I'll use the cheat command to speed up testing. This gives me 10000 of each resource."

**Action:** Type `info resources`  
**Say:** "Now I have plenty of resources to continue."

#### 2.6 Building Consumer
**Action:** Type `build bakeryshop`  
**Say:** "I'll build a bakery shop for 500 CREDITS. This is a consumer that consumes cakes to level up."

**Action:** Type `tick` (repeat 3-4 times)  
**Say:** "Each tick, the bakery shop consumes cakes based on its level and increases its level."

**Action:** Type `info entities`  
**Say:** "You can see the bakery shop's current level."

#### 2.7 Final Action - Selling
**Action:** Type `sell bakeryshop1` (or actual shop name)  
**Say:** "I'll sell cakes from the bakery shop. This earns credits based on the shop's level and resets it to level 1."

**Action:** Type `info resources`  
**Say:** "You can see I've earned credits from selling."

#### 2.8 Help Command
**Action:** Type `help`  
**Say:** "The help command shows all available commands."

**Action:** Type `help graph`  
**Say:** "You can also get help on specific commands."

---

### Section 3: New Features - Graph (1 minute)
**Action:** Type `graph CAKE`  
**Say:** "The graph command displays a text-based visualization of a resource over time. This shows how CAKE production has progressed."

**Action:** Type `graph CREDITS`  
**Say:** "I can graph any resource. Here's the credits history."

**Action:** Switch to IDE/editor  
**Action:** Open `src/org/uob/a2/parser/GraphCommand.java`  
**Say:** "The GraphCommand class retrieves the history from SimulationState, finds the maximum value, and creates a bar chart using hash symbols. Each bar represents the resource amount at that tick."

**Action:** Scroll through the code, highlighting key parts  
**Say:** "It uses a fixed width of 60 characters and scales each bar proportionally to the maximum value."

---

### Section 4: New Features - Save (1 minute)
**Action:** Switch back to terminal  
**Action:** Type `save mybakery.csv`  
**Say:** "The save command saves the current simulation state to a CSV file in the data directory."

**Action:** Switch to IDE/editor  
**Action:** Open `src/org/uob/a2/parser/SaveCommand.java`  
**Say:** "SaveCommand calls the Engine's save method."

**Action:** Open `src/org/uob/a2/engine/Engine.java`  
**Action:** Scroll to the `save` method (around line 62)  
**Say:** "The save method writes the current tick, all resources, and all entities to a CSV file. Each entity includes its type, name, class, and state information."

**Action:** Show the data directory if possible  
**Say:** "The file is saved in the data directory with all simulation state preserved."

---

### Section 5: New Features - Load (1 minute)
**Action:** Switch back to terminal  
**Action:** Type `tick` (a few times to change state)  
**Action:** Type `info resources`  
**Say:** "Let me advance the simulation a bit to change the state."

**Action:** Type `load mybakery.csv`  
**Say:** "Now I'll load the saved game."

**Action:** Type `info resources`  
**Say:** "The simulation has been restored to the exact state when I saved it."

**Action:** Switch to IDE/editor  
**Action:** Open `src/org/uob/a2/parser/LoadCommand.java`  
**Say:** "LoadCommand calls the Engine's load method."

**Action:** Open `src/org/uob/a2/engine/Engine.java`  
**Action:** Scroll to the `load` method (around line 106)  
**Say:** "The load method reads the CSV file, clears existing entities, and recreates them from the file. It restores resources, entities, and the tick number."

---

### Section 6: Main.java Explanation (1 minute)
**Action:** Switch to IDE/editor  
**Action:** Open `src/org/uob/a2/Main.java`  
**Say:** "Main.java is the entry point of the simulation."

**Action:** Point to lines 9-12  
**Say:** "It creates a SimulationState, Engine, Context, and Parser. The Engine is initialized with 1000 CREDITS."

**Action:** Point to lines 14-16  
**Say:** "It uses a Scanner to read user input and displays a welcome message."

**Action:** Point to lines 19-34  
**Say:** "The main loop reads commands, parses them using the Parser, executes them through the Command pattern, and displays results. The loop continues until a QuitCommand is executed."

**Action:** Point to line 24  
**Say:** "The parser converts text input into Command objects."

**Action:** Point to line 26  
**Say:** "Each command's execute method receives the Context, which contains both the Engine and SimulationState."

---

### Section 7: Additional Features (30 seconds)
**Action:** Switch to IDE/editor  
**Action:** Show model classes (e.g., `BakeryShop.java`, `Oven.java`, `WheatFarm.java`)  
**Say:** "I've created custom concrete classes for the bakery simulation - producers like WheatFarm and SugarFarm, converters like FlourMill and Oven, and the BakeryShop consumer. These extend the base Producer, Converter, and Consumer classes."

**Action:** Show any additional helper methods or classes if created  
**Say:** "[Mention any other custom features, methods, or classes you've added beyond the requirements]"

---

### Section 8: Conclusion (30 seconds)
**Action:** Switch back to terminal  
**Action:** Type `quit`  
**Say:** "The simulation supports all required commands and features. Thank you for watching."

---

## TIMING BREAKDOWN

| Section | Duration |
|---------|----------|
| Introduction & Identity | 30s |
| Running Simulation | 2m |
| Graph Feature | 1m |
| Save Feature | 1m |
| Load Feature | 1m |
| Main.java Explanation | 1m |
| Additional Features | 30s |
| Conclusion | 30s |
| **TOTAL** | **~7m 30s** |

**Note:** Adjust timing as needed to stay under 7 minutes. You can:
- Speed up command demonstrations
- Reduce pauses between commands
- Condense explanations
- Skip showing some code details if time is tight

---

## KEY POINTS TO REMEMBER

1. **Show face and student card** at the beginning (REQUIRED)
2. **Demonstrate ALL required commands:**
   - build/b
   - info/i
   - graph/g
   - save/s
   - load/l
   - tick/t
   - help
   - sell (launch command)
   - cheat
   - quit

3. **Explain Main.java** - show the main loop, parser, and command execution

4. **Explain new features:**
   - Graph: how it visualizes resource history
   - Save: how it writes state to CSV
   - Load: how it restores state from CSV

5. **Show custom classes** - demonstrate your bakery simulation entities

6. **Keep it under 7 minutes** - practice timing beforehand

---

## TIPS FOR RECORDING

- Practice the script once before recording
- Use the cheat command liberally to speed up demonstrations
- Have code files ready to open quickly
- Speak clearly and at a moderate pace
- If you make a mistake, pause and restart that section
- Ensure good lighting and audio quality
- Test your recording setup before starting

---

## COMMAND REFERENCE (Quick Copy-Paste)

```
info resources
info entities
build wheatfarm
build sugarfarm
build chickencoop
build dairyfarm
tick
tick
tick
info resources
build flourmill
build oven
cheat
build bakeryshop
tick
tick
tick
info entities
sell bakeryshop1
info resources
help
help graph
graph CAKE
graph CREDITS
save mybakery.csv
tick
tick
info resources
load mybakery.csv
info resources
quit
```
