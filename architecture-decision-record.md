### ADR-01: Change Configuration Code and Do Not Change WadLoader Code

Date: 8/18/2026
Context: Changing/Creating configuration loading code is necessary to add my new configurations, and mutating the WadLoader code would be unnecessary and catastrophic if done poorly.

### ADR-02: Write Custom Code for Reading and Writing Config File

Date: 8/29/2026
Context: Invoking the Settings Enum (Which is invoked by the existing configuration manager) launches the engine. I decided to avoid using the existing tooling, as using it would mean refactoring the existing settings enum to avoid engine calls.


## ADR-03: Move In-memory Configuration Values to the ConfigHelper class

Date: 8/29/2026
Context: Since I don't ever specify where to put my in-memory variables in my tasks, I decided to move it to the ConfigHelper class. This makes the most sense to me.

## ADR-04 Change as Little of the Existing Code as Possible

Date: 8/29/2026
Context: Since it seems a lot of the existing tooling is tied to the engine, It appears to be easier to create my own, simpler interpretations of these tools to reach my goals for this project.
