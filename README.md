Pool Balance ( Personal Edition )
---------------------------------
>Pool cleaning, measurement and chemical balancing app using ScalaFx, Scalikejdbc, Ox, HikariCP, H2, jDeploy and Scala 3.

Install
-------
1. Select [Pool Balance](https://www.jdeploy.com/~poolbalance)
2. Select a platform to download a compressed app installer.
3. Decompress app installer.
4. Install app by double-clicking app installer.
5. Select app icon to launch app.
>This install has been tested on macOS.

Features
--------
1. dashboard
2. cleanings
3. measurements
4. chemicals
5. charts
6. database

Use Cases
---------
1. clean pool and components
2. measure pool chemical content
3. add chemicals to pool

Entity
------
* Pool 1 --> * Cleaning | Measurement | Chemical

Model
-----
* App 1 --> 1 Context | Store | Model | View
* Store 1 --> 1 Context
* Model 1 --> 1 Context | Store
* View 1 --> 1 Context | Model

UI
--
1. Top: dashboard(total chlorine, free chlorine, ph, calcium hardness, total alkalinity, cyanuric acid, total bromine)
2. Left: pane(pools)
3. Center: tabbedpane(cleanings, measurements, chemicals)

Charts
------
* cleanings - line chart ( x = cleaned, y = ? )
* measurements - line chart ( x = measured, y = measurement )
* chemicals - bar chart ( x = added, y = amount/typeof )

Measurements
------------
>Measured in ppm ( parts per million ).

| Measurement                       | Range     | Good        | Ideal |
|-----------------------------------|-----------|-------------|-------|
| total chlorine (tc = fc + cc)     | 0 - 10    | 1 - 5       | 3     |
| free chlorine (fc)                | 0 - 10    | 1 - 5       | 3     |
| combinded chlorine (cc = tc - fc) | 0.0 - 0.5 | 0.0 - 0.2   | 0.0   |
| ph                                | 6.2 - 8.4 | 7.2 - 7.6   | 7.4   |
| calcium hardness                  | 0 - 1000  | 250 - 500   | 375   |
| total alkalinity                  | 0 - 240   | 80 - 120    | 100   |
| cyanuric acid                     | 0 - 300   | 30 - 100    | 50    |
| total bromine                     | 0 - 20    | 2 - 10      | 5     |
| salt                              | 0 - 3600  | 2700 - 3400 | 3200  |
| temperature                       | 50 - 100  | 75 - 85     | 82    |

Chemicals
---------
* Liquids measured in: gallons ( gl ) and liters ( l ).
* Granules measured in: pounds ( lbs ) and kilograms ( kg ).
1. LiquidChlorine ( gl/l )
2. Trichlor ( tablet )
3. Dichlor ( lbs/kg )
4. CalciumHypochlorite ( lbs/kg )
5. Stabilizer ( lbs/kg )
6. Algaecide ( gl/l )
7. MuriaticAcid ( gl/l )
8. Salt ( lbs/kg )

Products
--------
1. Chlorine for pool.
2. Chlorine tablets for pool filtration system.
3. Pool Shock

Solutions
---------
>Suggested solutions to chemical imbalances.
1. high ph - Sodium Bisulfate
2. low ph - Sodium Carbonate, Soda Ash
3. high alkalinity - Muriatic Acid, Sodium Bisulfate
4. low alkalinity - Sodium Bicarbonate, Baking Soda
5. calcium hardness - Calcium Chloride
6. low chlorine - Chlorine Tablets, Granules, Liquid
7. algae - Algaecide, Shock
8. stains - Stain Identification Kit, Stain Remover

Build
-----
1. ```sbt clean compile```

Test
----
1. ```sbt clean test```

Run
---
1. ```sbt run```

Assembly
--------
1. ```sbt clean test assembly copyAssemblyJar```

Execute
-------
1. ```java -jar .assembly/poolbalance-$version.jar```

Deploy
------
1. edit build.sbt ( jarVersion + version )
2. edit app.conf ( about > alert > contentText )
3. edit package.json ( version + jdeploy / jar )
4. sbt clean test assembly copyAssemblyJar
5. perform github release ( from https://github.com/objektwerks/pool.balance.p )
6. npm login
7. jdeploy publish ( to https://www.jdeploy.com/~poolbalance )
8. check email for npm message
>See [jDeploy Docs](https://www.jdeploy.com/docs/manual/#_getting_started) for details.

jDeploy Install
---------------
1. Setup npm account at npmjs.com
2. Install node, which installs npm, which bundles npx.
3. Install jdeploy via npm - *npm install -g jdeploy*
4. Add icon.png ( 256x256 or 512x512 ) to project root and resources.
5. Edit jDeploy *package.json* as required.
6. Add *jdeploy* and *jdeploy-bundle* to .gitignore
>See [jDeploy Docs](https://www.jdeploy.com/docs/manual/#_getting_started) for details.

NPM Versioning
--------------
>The ```build.sbt``` **must** contain a ```semver 3-digit``` **version** number. See: [Npmjs Semver](https://docs.npmjs.com/about-semantic-versioning)

NPM Registry
------------
>Pool Balance is deployed to: https://www.npmjs.com/package/poolbalance

Resources
---------
* [ScalaFX](http://www.scalafx.org/)
* [JavaFX](https://jenkov.com/tutorials/javafx/index.html)
* [ScalikeJdbc](http://scalikejdbc.org/)
* [jDeploy](https://www.jdeploy.com/)

License
-------
>Copyright (c) [2022-2025] [Objektwerks]

>Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    * http://www.apache.org/licenses/LICENSE-2.0

>Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.