package com.example.chemlabx.data

import com.example.chemlabx.data.model.*

object OrganicHydrocarbonsData {
    val alkanes = OrganicFunctionalGroup(
        id = "alkanes",
        name = "Alkanes",
        hindiName = "एल्केन (संतृप्त हाइड्रोकार्बन)",
        category = OrganicCategory.HYDROCARBON,
        generalFormula = "CnH2n+2 (R–H)",
        condensedStructure = "–C–C–",
        visualStructure2D = "  H   H\n  |   |\n– C – C –\n  |   |\n  H   H",
        iupacSuffix = "-ane",
        iupacPrefix = "alkyl- (e.g. methyl-, ethyl-)",
        priorityOrder = 15,
        hybridization = "sp³ hybridized carbon",
        bondAngle = "109.5° (Tetrahedral)",
        polarity = "Nonpolar (van der Waals / London dispersion forces)",
        boilingPointTrend = "Increases with carbon chain length (~20-30°C per CH₂ unit); decreases with branching due to lower surface area.",
        solubility = "Insoluble in water (hydrophobic); highly soluble in nonpolar organic solvents like benzene, ether, and CCl₄.",
        acidityBasicity = "Essentially neutral (pKa ~ 50), extremely inert to acids and bases.",
        description = "Saturated aliphatic hydrocarbons containing only single C–C and C–H covalent sigma bonds. Known historically as paraffins due to low reactivity.",
        iupacNamingRules = listOf(
            "1. Find the longest continuous carbon chain (parent chain).",
            "2. Number chain from end giving substituents lowest possible locants.",
            "3. Name substituents alphabetically with positional numbers.",
            "4. Use prefixes di-, tri-, tetra- for multiple identical substituents without affecting alphabetical order."
        ),
        examples = listOf(
            OrganicExample("Methane", "Marsh Gas / Biogas", "CH₄", "CH₄", "H–CH₃", "Natural gas component, tetrahedral geometry, fuel"),
            OrganicExample("Ethane", "Dimethyl", "C₂H₆", "CH₃–CH₃", "CH₃–CH₃", "Constituent of natural gas, precursor to ethylene"),
            OrganicExample("2-Methylpropane", "Isobutane", "C₄H₁₀", "CH(CH₃)₃", "CH₃–CH(CH₃)–CH₃", "Branched alkane used as aerosol propellant and refrigerant")
        ),
        reactions = listOf(
            OrganicReaction(
                id = "alkane_combustion",
                name = "Complete Combustion",
                reactionType = OrganicReactionType.CHEMICAL_PROPERTY,
                equation = "CH₄ + 2O₂ → CO₂ + 2H₂O + Heat (ΔH = -891 kJ/mol)",
                reactants = "Methane, Oxygen",
                products = "Carbon dioxide, Water vapor",
                reagentsAndCatalyst = "Spark / Ignition",
                conditions = "Excess oxygen, high temperature",
                mechanismType = "Free Radical Chain Oxidation",
                mechanismSteps = listOf(
                    "Thermal initiation cleaves bonds producing radical intermediates.",
                    "Rapid propagation transfers hydrogen atoms to oxygen radicals.",
                    "Exothermic conversion yields thermodynamically stable CO₂ and H₂O."
                ),
                keyObservation = "Luminous/blue smokeless flame with massive heat liberation",
                importance = "Primary source of thermal and electrical energy worldwide."
            ),
            OrganicReaction(
                id = "alkane_halogenation",
                name = "Free Radical Halogenation",
                reactionType = OrganicReactionType.CHEMICAL_PROPERTY,
                equation = "CH₄ + Cl₂ → [hν / UV] CH₃Cl + HCl",
                reactants = "Methane, Chlorine",
                products = "Chloromethane, Hydrogen chloride",
                reagentsAndCatalyst = "Ultraviolet light (hν) or heat (300-400°C)",
                conditions = "Gas phase, photochemical excitation",
                mechanismType = "Free Radical Substitution (SR)",
                mechanismSteps = listOf(
                    "Initiation: Homolytic cleavage of Cl–Cl bond by photon to yield 2 Cl• radicals.",
                    "Propagation 1: Cl• abstracts H from CH₄ forming •CH₃ (methyl radical) and HCl.",
                    "Propagation 2: •CH₃ attacks Cl₂ to yield CH₃Cl and regenerates Cl• radical.",
                    "Termination: Radical recombination (•CH₃ + •CH₃ → C₂H₆ or •CH₃ + Cl• → CH₃Cl)."
                ),
                keyObservation = "Decoloration of greenish-yellow chlorine gas with evolution of acidic HCl fumes",
                importance = "Industrial conversion of petroleum alkanes into versatile synthetic intermediates."
            ),
            OrganicReaction(
                id = "wurtz_reaction",
                name = "Wurtz Reaction (Preparation of Alkanes)",
                reactionType = OrganicReactionType.NAMED_REACTION,
                equation = "2R–X + 2Na → [Dry Ether] R–R + 2NaX",
                reactants = "Alkyl halide, Sodium metal",
                products = "Symmetrical alkane, Sodium halide",
                reagentsAndCatalyst = "Sodium metal in dry ether solvent",
                conditions = "Anhydrous conditions strictly required",
                mechanismType = "Organometallic / Free Radical coupling",
                mechanismSteps = listOf(
                    "Na donates electron to R–X to form an organosodium intermediate (R–Na) or R• free radical.",
                    "The carbanionic species or radical couples with a second molecule of R–X to form R–R."
                ),
                keyObservation = "Vigorous reaction with dissolution of sodium and precipitation of white NaX salt",
                importance = "Classic method to double carbon chain length into symmetrical alkanes."
            )
        ),
        identificationTests = listOf(
            OrganicTest(
                testName = "Bromine Water Test (Inertness Test)",
                reagent = "5% Br₂ in CCl₄ or Bromine water",
                observation = "No decolorization in the dark; reddish-brown color persists.",
                explanation = "Alkanes are saturated and lack π-bonds; they do not undergo electrophilic addition.",
                chemicalEquation = "R–H + Br₂ (dark) → No Reaction"
            )
        )
    )

    val alkenes = OrganicFunctionalGroup(
        id = "alkenes",
        name = "Alkenes",
        hindiName = "एल्कीन (द्वि-आबंध)",
        category = OrganicCategory.HYDROCARBON,
        generalFormula = "CnH2n (R–CH=CH–R')",
        condensedStructure = "–C=C–",
        visualStructure2D = "  R     R'\n   \\   /\n    C = C\n   /   \\\n  H     H",
        iupacSuffix = "-ene",
        iupacPrefix = "alkenyl- (e.g. ethenyl / vinyl-)",
        priorityOrder = 14,
        hybridization = "sp² hybridized carbon",
        bondAngle = "~120° (Trigonal Planar)",
        polarity = "Weakly polar to nonpolar; slightly higher dipole moment than alkanes for cis-isomers.",
        boilingPointTrend = "Close to corresponding alkanes; cis-isomer has higher boiling point than trans-isomer due to net dipole moment.",
        solubility = "Insoluble in water, soluble in nonpolar organic solvents.",
        acidityBasicity = "More acidic than alkanes (pKa ~ 44) due to higher s-character (33%) of sp² hybridized carbon.",
        description = "Unsaturated hydrocarbons containing at least one carbon-carbon double bond consisting of one strong σ bond and one exposed π bond.",
        iupacNamingRules = listOf(
            "1. Longest chain MUST contain the double bond (C=C).",
            "2. Number chain from the end closer to C=C, giving double-bonded carbons lowest locants.",
            "3. Suffix -ane is replaced by -ene (or -diene, -triene).",
            "4. Specify stereochemistry using cis/trans or E/Z nomenclature."
        ),
        examples = listOf(
            OrganicExample("Ethene", "Ethylene", "C₂H₄", "CH₂=CH₂", "CH₂=CH₂", "Plant ripening hormone, major monomer for polyethylene plastic"),
            OrganicExample("Propene", "Propylene", "C₃H₆", "CH₃–CH=CH₂", "CH₃–CH=CH₂", "Used to synthesize polypropylene and acetone"),
            OrganicExample("trans-But-2-ene", "trans-Dimethylethylene", "C₄H₈", "CH₃–CH=CH–CH₃", "CH₃–CH=CH–CH₃ (trans)", "Stereoisomer with zero dipole moment")
        ),
        reactions = listOf(
            OrganicReaction(
                id = "electrophilic_addition_hbr",
                name = "Markovnikov Addition of HBr",
                reactionType = OrganicReactionType.CHEMICAL_PROPERTY,
                equation = "CH₃–CH=CH₂ + HBr → CH₃–CH(Br)–CH₃ (2-Bromopropane)",
                reactants = "Propene, Hydrogen bromide",
                products = "2-Bromopropane (Major product)",
                reagentsAndCatalyst = "HBr without peroxides",
                conditions = "Room temperature, dark / inert solvent",
                mechanismType = "Electrophilic Addition (AdE)",
                mechanismSteps = listOf(
                    "Electrophilic attack: π-electrons attack H⁺ from HBr forming more stable 2° carbocation intermediate.",
                    "Nucleophilic attack: Bromide ion (Br⁻) rapidly attacks the 2° carbocation forming 2-bromopropane."
                ),
                keyObservation = "Rapid absorption of HBr gas without gas evolution",
                importance = "Fundamental demonstration of Markovnikov's rule governing regioselectivity."
            ),
            OrganicReaction(
                id = "ozonolysis",
                name = "Ozonolysis of Alkenes",
                reactionType = OrganicReactionType.NAMED_REACTION,
                equation = "R–CH=CH–R' + O₃ → [Zn / H₂O] R–CHO + R'–CHO + ZnO",
                reactants = "Alkene, Ozone",
                products = "Aldehydes or Ketones",
                reagentsAndCatalyst = "Ozone (O₃) followed by reductive workup with Zn dust / H₂O or (CH₃)₂S",
                conditions = "Low temperature (-78°C in CH₂Cl₂), then reductive cleavage",
                mechanismType = "Cycloaddition & Rearrangement",
                mechanismSteps = listOf(
                    "1,3-Dipolar cycloaddition of O₃ across C=C forms molozonide.",
                    "Rearrangement into more stable ozonide (1,2,4-trioxolane).",
                    "Reductive cleavage by Zn prevents oxidation of aldehydes to carboxylic acids."
                ),
                keyObservation = "Cleavage of the double bond revealing exact location of unsaturation",
                importance = "Crucial analytical tool for structure elucidation of unknown alkenes and natural products."
            )
        ),
        identificationTests = listOf(
            OrganicTest(
                testName = "Baeyer's Test (Cold Dilute KMnO₄)",
                reagent = "1% Cold alkaline KMnO₄ solution",
                observation = "Purple color of KMnO₄ discharges; brown precipitate of MnO₂ forms.",
                explanation = "Alkene is oxidized to a vicinal 1,2-diol (glycol) via syn-dihydroxylation.",
                chemicalEquation = "CH₂=CH₂ + H₂O + [O] → [Cold KMnO₄] CH₂(OH)–CH₂(OH) + MnO₂ (brown ppt)"
            ),
            OrganicTest(
                testName = "Bromine Decolorization Test",
                reagent = "Bromine in CCl₄ (reddish-orange)",
                observation = "Instant decolorization of the reddish-brown bromine solution without evolution of HBr.",
                explanation = "Electrophilic addition of Br₂ across the π-bond produces vicinal dibromide.",
                chemicalEquation = "CH₂=CH₂ + Br₂ → CH₂Br–CH₂Br (colorless 1,2-dibromoethane)"
            )
        )
    )

    val alkynes = OrganicFunctionalGroup(
        id = "alkynes",
        name = "Alkynes",
        hindiName = "एल्काइन (त्रि-आबंध)",
        category = OrganicCategory.HYDROCARBON,
        generalFormula = "CnH2n-2 (R–C≡C–R')",
        condensedStructure = "–C≡C–",
        visualStructure2D = "R – C ≡ C – R'",
        iupacSuffix = "-yne",
        iupacPrefix = "alkynyl- (e.g. ethynyl-)",
        priorityOrder = 13,
        hybridization = "sp hybridized carbon",
        bondAngle = "180° (Linear)",
        polarity = "Weakly polar; terminal alkynes have weak dipole moment.",
        boilingPointTrend = "Slightly higher boiling points than corresponding alkanes and alkenes due to linear cylindrical π-electron density.",
        solubility = "Insoluble in water, soluble in organic solvents.",
        acidityBasicity = "Terminal alkynes are notably acidic (pKa ~ 25) due to 50% s-character of sp carbon stabilizing carbanion.",
        description = "Unsaturated hydrocarbons characterized by one or more carbon-carbon triple bonds containing one σ bond and two mutually perpendicular π bonds.",
        iupacNamingRules = listOf(
            "1. Select the longest continuous carbon chain containing the triple bond.",
            "2. Number chain from the end closer to C≡C.",
            "3. If both double and triple bonds are present, give lowest locants to unsaturation; if tied, double bond gets lower number (-en-yne)."
        ),
        examples = listOf(
            OrganicExample("Ethyne", "Acetylene", "C₂H₂", "CH≡CH", "H–C≡C–H", "Used in oxy-acetylene welding torches (temperatures > 3000°C)"),
            OrganicExample("Propyne", "Methylacetylene", "C₃H₄", "CH₃–C≡CH", "CH₃–C≡C–H", "Terminal alkyne, rocket fuel component (MAPP gas)"),
            OrganicExample("But-2-yne", "Dimethylacetylene", "C₄H₆", "CH₃–C≡C–CH₃", "CH₃–C≡C–CH₃", "Internal alkyne, symmetric linear molecule")
        ),
        reactions = listOf(
            OrganicReaction(
                id = "alkyne_hydration",
                name = "Kucherov Reaction (Hydration of Alkynes)",
                reactionType = OrganicReactionType.NAMED_REACTION,
                equation = "CH≡CH + H₂O → [1% HgSO₄ / 20% H₂SO₄, 333 K] CH₃–CHO (Acetaldehyde)",
                reactants = "Ethyne, Water",
                products = "Acetaldehyde (Ethanol)",
                reagentsAndCatalyst = "1% Mercuric sulfate (HgSO₄) + 20% Dilute Sulfuric acid (H₂SO₄)",
                conditions = "60°C (333 K)",
                mechanismType = "Electrophilic Addition followed by Keto-Enol Tautomerism",
                mechanismSteps = listOf(
                    "Electrophilic attack by Hg²⁺ on alkyne generates cyclic mercurinium ion.",
                    "Attack by water forms an unstable enol intermediate [CH₂=CH–OH].",
                    "Rapid keto-enol tautomerization drives equilibrium toward stable carbonyl compound."
                ),
                keyObservation = "Conversion of pungent acetylene gas into characteristic sweet aldehyde aroma",
                importance = "Direct synthetic route from industrial alkynes to vital carbonyl precursors."
            )
        ),
        identificationTests = listOf(
            OrganicTest(
                testName = "Tollens' Test for Terminal Alkynes",
                reagent = "Ammoniacal Silver Nitrate [Ag(NH₃)₂]⁺",
                observation = "Formation of white/grey precipitate of Silver Acetylide.",
                explanation = "Acidic terminal acetylenic proton is replaced by Ag⁺ ion.",
                chemicalEquation = "R–C≡CH + [Ag(NH₃)₂]⁺ → R–C≡C–Ag ↓ (White ppt) + NH₄⁺ + NH₃"
            ),
            OrganicTest(
                testName = "Ammoniacal Cuprous Chloride Test",
                reagent = "Cu₂Cl₂ in aqueous ammonia",
                observation = "Red precipitate of Copper(I) Acetylide.",
                explanation = "Terminal alkynes form insoluble red copper(I) acetylide complexes.",
                chemicalEquation = "R–C≡CH + Cu(NH₃)₂⁺ → R–C≡C–Cu ↓ (Red ppt) + NH₄⁺ + NH₃"
            )
        )
    )

    val arenes = OrganicFunctionalGroup(
        id = "arenes",
        name = "Aromatic Hydrocarbons (Arenes)",
        hindiName = "ऐरोमैटिक हाइड्रोकार्बन (बेंजीन व व्युत्पन्न)",
        category = OrganicCategory.HYDROCARBON,
        generalFormula = "C6H5–R (Ar–H)",
        condensedStructure = "C₆H₅–",
        visualStructure2D = "    / \\ \n   |   |   (delocalized 6π electrons)\n    \\ / ",
        iupacSuffix = "-benzene (e.g. chlorobenzene, nitrobenzene)",
        iupacPrefix = "phenyl- (C₆H₅–) or benzyl- (C₆H₅CH₂–)",
        priorityOrder = 16,
        hybridization = "sp² hybridized planar carbon ring",
        bondAngle = "120° (Planar Hexagon)",
        polarity = "Nonpolar (Benzene dipole moment = 0 D)",
        boilingPointTrend = "Higher boiling and melting points than aliphatic analogues due to flat ring stacking and π–π interactions.",
        solubility = "Immiscible with water, freely miscible with organic solvents.",
        acidityBasicity = "Slightly acidic C–H (pKa ~ 43), ring acts as electron-rich nucleophile.",
        description = "Cyclic planar conjugated hydrocarbons obeying Hückel's Rule (4n+2 π electrons). Extraordinary thermodynamic resonance stability (~150 kJ/mol).",
        iupacNamingRules = listOf(
            "1. Monosubstituted benzenes are named with substituent prefix followed by 'benzene'.",
            "2. Disubstituted benzenes use 1,2- (ortho-), 1,3- (meta-), or 1,4- (para-) positions.",
            "3. High-priority common names like toluene, phenol, aniline, and benzoic acid are accepted as parent IUPAC names."
        ),
        examples = listOf(
            OrganicExample("Benzene", "Benzol", "C₆H₆", "C₆H₆", "C₆H₆", "Parent aromatic compound, universal industrial solvent and precursor"),
            OrganicExample("Methylbenzene", "Toluene", "C₇H₈", "C₆H₅–CH₃", "C₆H₅CH₃", "Precursor to polyurethane, TNT, and benzoic acid"),
            OrganicExample("1,4-Dimethylbenzene", "p-Xylene", "C₈H₁₀", "p-C₆H₄(CH₃)₂", "CH₃–C₆H₄–CH₃", "Industrial precursor to terephthalic acid for PET plastic bottles")
        ),
        reactions = listOf(
            OrganicReaction(
                id = "electrophilic_aromatic_substitution_nitration",
                name = "Nitration of Benzene",
                reactionType = OrganicReactionType.CHEMICAL_PROPERTY,
                equation = "C₆H₆ + HNO₃ → [Conc. H₂SO₄, 55°C] C₆H₅NO₂ + H₂O",
                reactants = "Benzene, Concentrated Nitric Acid",
                products = "Nitrobenzene, Water",
                reagentsAndCatalyst = "Nitrating mixture: Conc. HNO₃ + Conc. H₂SO₄ (1:1)",
                conditions = "50–55°C temperature control",
                mechanismType = "Electrophilic Aromatic Substitution (SEAr)",
                mechanismSteps = listOf(
                    "Generation of nitronium ion (NO₂⁺): HNO₃ acts as a base accepting proton from H₂SO₄.",
                    "Attack of benzene π-system on NO₂⁺ to form resonance-stabilized arenium ion (sigma complex / Wheland intermediate).",
                    "Loss of proton (H⁺) to HSO₄⁻ to restore aromaticity and aromatic stabilization energy."
                ),
                keyObservation = "Formation of pale yellow oily liquid with bitter almond odor (Nitrobenzene)",
                importance = "Core industrial process to manufacture aniline, dyes, pharmaceuticals, and polymers."
            ),
            OrganicReaction(
                id = "friedel_crafts_alkylation",
                name = "Friedel-Crafts Alkylation",
                reactionType = OrganicReactionType.NAMED_REACTION,
                equation = "C₆H₆ + CH₃Cl → [Anhydrous AlCl₃] C₆H₅–CH₃ + HCl",
                reactants = "Benzene, Chloromethane",
                products = "Toluene (Methylbenzene), Hydrogen chloride",
                reagentsAndCatalyst = "Anhydrous Aluminum Chloride (AlCl₃) as Lewis Acid catalyst",
                conditions = "Room temperature, anhydrous",
                mechanismType = "Electrophilic Aromatic Substitution",
                mechanismSteps = listOf(
                    "AlCl₃ coordinates with CH₃Cl to generate a polarized carbocation complex [CH₃⁺ ... AlCl₄⁻].",
                    "Electrophilic attack of benzene ring onto CH₃⁺ yields sigma complex.",
                    "Loss of proton restores aromaticity; AlCl₃ is regenerated with evolution of HCl gas."
                ),
                keyObservation = "Evolution of acidic white choking fumes of HCl",
                importance = "Standard methodology for introducing alkyl groups onto aromatic rings."
            )
        ),
        identificationTests = listOf(
            OrganicTest(
                testName = "Sooty Flame Test",
                reagent = "Combustion on spatula",
                observation = "Burns with a bright, smoky, yellow flame producing dense black soot.",
                explanation = "High carbon-to-hydrogen ratio in aromatic rings causes incomplete combustion.",
                chemicalEquation = "C₆H₆ + O₂ (limited) → C (soot) + CO + CO₂ + H₂O"
            )
        )
    )
}
