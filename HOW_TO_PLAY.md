# How to Play: Bakery Simulation User Story

## Introduction

Welcome to the Bakery Simulation! In this text-based simulation, I'm running a bakery business where I need to manage resources, build production facilities, and create delicious cakes to sell for credits. The goal is to build a thriving bakery empire by strategically managing my resources and production chain.

When I start the simulation, I begin with **1000 CREDITS** to get my business started. I'll need to build producers to gather raw materials, converters to process them, and finally a bakery shop to sell my finished products.

---

## Core Gameplay Flow

### Step 1: Checking My Starting State

First, I want to see what resources I have available. I type:

```
> info resources
```

This shows me my current resources. I can see I have 1000 CREDITS to start with, and no other resources yet. I can also check what entities I have built (which will be none at the start) by typing:

```
> info entities
```

### Step 2: Building My First Producers

To create cakes, I need raw ingredients. I'll start by building producers that gather basic resources. Let me build a wheat farm first:

```
> build wheatfarm
```

This costs me 100 CREDITS and creates a wheat farm that will produce 10 WHEAT every time I advance the simulation. I'll build a few more producers:

```
> build sugarfarm
> build chickencoop
> build dairyfarm
```

Now I have:
- **WheatFarm** (100 CREDITS) - produces 10 WHEAT per tick
- **SugarFarm** (100 CREDITS) - produces 10 SUGAR per tick
- **ChickenCoop** (150 CREDITS) - produces 5 EGGS per tick
- **DairyFarm** (150 CREDITS) - produces 10 MILK per tick

I've spent 500 CREDITS so far, leaving me with 500 CREDITS remaining.

### Step 3: Advancing Time to Produce Resources

Now I need to let time pass so my producers can create resources. I use the `tick` command:

```
> tick
```

This advances the simulation by one tick. All my producers work simultaneously:
- My wheat farm produces 10 WHEAT
- My sugar farm produces 10 SUGAR
- My chicken coop produces 5 EGGS
- My dairy farm produces 10 MILK

I can check my resources again to see the changes:

```
> info resources
```

I'll need to tick multiple times to gather enough resources for the next steps. Let me tick a few more times to build up my inventory.

### Step 4: Building Converters

To make cakes, I need to process my raw materials. First, I'll build a flour mill to convert wheat into flour:

```
> build flourmill
```

This costs 200 CREDITS. The flour mill converts 10 WHEAT into 5 FLOUR every tick (when I have enough wheat available).

Now I need an oven to bake cakes. The oven is more expensive:

```
> build oven
```

This costs 300 CREDITS. The oven requires multiple ingredients to make one cake:
- 5 FLOUR
- 5 SUGAR
- 2 EGGS
- 5 MILK

This produces 1 CAKE per tick when all ingredients are available.

I've now spent all 1000 of my starting credits! I'll need to produce and sell cakes to earn more.

### Step 5: Converting Resources

Now I need to tick the simulation to let my converters work. First, my flour mill will convert wheat to flour:

```
> tick
```

My producers create more raw materials, and my flour mill converts 10 WHEAT into 5 FLOUR. I continue ticking until I have enough ingredients for the oven:

```
> tick
> tick
```

After several ticks, I should have accumulated enough FLOUR, SUGAR, EGGS, and MILK. Each tick, my oven will automatically convert the required ingredients into CAKE (if I have enough of each ingredient).

I can check my progress:

```
> info resources
```

I should see CAKE accumulating in my inventory!

### Step 6: Building a Consumer (Bakery Shop)

To sell my cakes and earn credits, I need to build a bakery shop. But wait - I don't have enough credits! I need to either:
- Use the cheat command to get more resources (for testing)
- Or wait until I can afford it

For now, let me use the cheat command to speed things up:

```
> cheat
```

This gives me 10000 of each resource, including CREDITS. Now I can build my bakery shop:

```
> build bakeryshop
```

The bakery shop costs 500 CREDITS. This is a consumer that will consume CAKE to level up. Each tick, it consumes 10 × (current level) CAKE and increases its level by 1.

### Step 7: Leveling Up the Bakery Shop

Now I need to feed cakes to my bakery shop. Each tick, the shop will consume cakes based on its level:
- Level 1: consumes 10 CAKE per tick
- Level 2: consumes 20 CAKE per tick
- Level 3: consumes 30 CAKE per tick
- And so on...

I tick the simulation:

```
> tick
```

My producers create more ingredients, my converters process them into cakes, and my bakery shop consumes cakes to level up. I can check the shop's status:

```
> info entities
```

This shows me all my entities, including the bakery shop and its current level.

### Step 8: Selling Cakes for Credits

Once my bakery shop has leveled up (reached at least level 2), I can sell the cakes to earn credits. The credits earned are calculated as: (level - 1) × 100 CREDITS.

For example:
- Level 2 shop: earns 100 CREDITS
- Level 3 shop: earns 200 CREDITS
- Level 5 shop: earns 400 CREDITS

After selling, the shop resets to level 1.

I use the sell command:

```
> sell bakeryshop1
```

(Replace "bakeryshop1" with the actual name of your bakery shop)

This earns me credits based on the shop's level, and the shop resets to level 1. Now I have more credits to build additional facilities and expand my bakery empire!

---

## Additional Features

### Visualizing Resource Changes with Graphs

I can visualize how my resources have changed over time using the graph command. For example, to see how my CAKE production has progressed:

```
> graph CAKE
```

This displays a text-based graph showing the amount of CAKE at each tick. This is useful for understanding production trends and planning my strategy.

I can graph any resource:

```
> graph CREDITS
> graph WHEAT
> graph FLOUR
```

### Saving and Loading Progress

I can save my current simulation state to a file. This is useful if I want to take a break or experiment with different strategies:

```
> save mybakery.csv
```

The game saves to a file in the `data/` directory. All my resources, entities, and the current tick number are saved.

Later, I can load this saved game:

```
> load mybakery.csv
```

This restores my simulation to exactly where I left off, including all my buildings, resources, and progress.

### Getting Help

If I ever forget a command or need more information, I can use the help command:

```
> help
```

This shows me all available commands. I can also get help on a specific command:

```
> help build
> help graph
> help sell
```

### Exiting the Simulation

When I'm done playing, I can exit with:

```
> quit
```

---

## Conclusion

The bakery simulation follows a natural progression:

1. **Start** with initial credits
2. **Build producers** to gather raw materials (wheat, sugar, eggs, milk)
3. **Advance time** with ticks to produce resources
4. **Build converters** to process raw materials (flour mill, oven)
5. **Convert resources** into finished products (cakes)
6. **Build consumers** (bakery shops) to prepare products for sale
7. **Level up shops** by feeding them cakes
8. **Sell products** to earn credits and expand

The cycle continues as I reinvest my earnings into more producers, converters, and shops to build a larger bakery operation. I can use graphs to track my progress, save my game to continue later, and experiment with different strategies to optimize my production chain.

### Tips for Continued Play

- Build multiple producers of the same type to increase production rates
- Balance your production chain - make sure you're producing enough of each ingredient
- Level up your bakery shops before selling for maximum profit
- Use the graph feature to identify bottlenecks in your production
- Save frequently to try different strategies without losing progress
- The cheat command is great for testing, but try playing without it for the full experience!

Happy baking!
