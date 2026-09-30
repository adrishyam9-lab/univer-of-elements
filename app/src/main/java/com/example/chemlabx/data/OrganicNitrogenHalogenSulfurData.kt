package com.example.chemlabx.data

import com.example.chemlabx.data.model.*

object OrganicNitrogenHalogenSulfurData {

    val amines = OrganicFunctionalGroup(
        id = "amines",
        name = "Amines",
        hindiName = "ऐमीन (-NH₂ समूह, कार्बनिक क्षारक)",
        category = OrganicCategory.NITROGEN_CONTAINING,
        generalFormula = "R–NH₂ (1°), R₂NH (2°), R₃N (3°)",
        condensedStructure = "–NH₂ / –NH– / –N<",
        visualStructure2D = "      H\n      |\n  R – N – H   (lone pair on N)",
        iupacSuffix = "-amine (e.g. ethanamine)",
        iupacPrefix = "amino-",
        priorityOrder = 10,
        hybridization = "Nitrogen is sp³ hybridized with one stereochemically active lone pair",
        bondAngle = "107° (Trigonal Pyramidal, undergoes rapid nitrogen inversion)",
        polarity = "Polar; 1° and 2° amines form intermolecular N–H···N hydrogen bonds",
        boilingPointTrend = "Higher than alkanes of similar molar mass, but lower than alcohols because N–H hydrogen bond is weaker than O–H bond.",
        solubility = "Lower aliphatic amines (C1–C3) are gaseous/volatile liquids and highly soluble in water forming alkaline solutions with fishy odor.",
        acidityBasicity = "Organic bases (pKb ~ 3–4); readily accept protons from acids to form water-soluble ammonium salts [R–NH₃⁺].",
        description = "Derivatives of ammonia in which one or more hydrogen atoms are replaced by alkyl or aryl groups. Essential building blocks of proteins, amino acids, and neurotransmitters.",
        iupacNamingRules = listOf(
            "1. Replace terminal -e of parent alkane with -amine (e.g., ethanamine).",
            "2. For secondary and tertiary amines, prefix the smaller alkyl groups with italic 'N-' (e.g., N-methylethanamine).",
            "3. If higher priority group is present (-COOH, -OH), amine is named as prefix 'amino-'."
        ),
        examples = listOf(
            OrganicExample("Methanamine", "Methylamine", "CH₅N", "CH₃NH₂", "CH₃–NH₂", "Pungent fishy gas used in pharmaceutical synthesis"),
            OrganicExample("Ethanamine", "Ethylamine", "C₂H₇N", "CH₃CH₂NH₂", "CH₃–CH₂–NH₂", "Raw material for herbicides and rubber chemicals"),
            OrganicExample("Benzenamine", "Aniline", "C₆H₇N", "C₆H₅NH₂", "C₆H₅–NH₂", "Industrial precursor to azo dyes, paracetamol, and polyurethane")
        ),
        reactions = listOf(
            OrganicReaction(
                id = "carbylamine_test_reaction",
                name = "Carbylamine Reaction (Hofmann's Isocyanide Test)",
                reactionType = OrganicReactionType.NAMED_REACTION,
                equation = "R–NH₂ + CHCl₃ + 3KOH → [Δ] R–NC (Isocyanide) + 3KCl + 3H₂O",
                reactants = "Primary aliphatic or aromatic amine, Chloroform, Alcoholic KOH",
                products = "Isocyanide (Carbylamine), Potassium chloride, Water",
                reagentsAndCatalyst = "Chloroform (CHCl₃) + Alcoholic KOH",
                conditions = "Gentle heating",
                mechanismType = "Alpha-elimination generating :CCl₂ followed by nucleophilic amine attack",
                mechanismSteps = listOf(
                    "KOH generates reactive dichlorocarbene intermediate (:CCl₂).",
                    "Primary amine lone pair attacks carbene.",
                    "Subsequent eliminations of two HCl molecules yield extremely foul-smelling isocyanide (–N≡C)."
                ),
                keyObservation = "Extremely offensive, nauseating, intolerable odor of carbylamine (Isocyanide)",
                importance = "Exclusive diagnostic test for PRIMARY (1°) amines; secondary and tertiary amines do not react."
            ),
            OrganicReaction(
                id = "hinsberg_reaction",
                name = "Hinsberg Reaction (Distinction of 1°, 2°, 3° Amines)",
                reactionType = OrganicReactionType.NAMED_REACTION,
                equation = "R–NH₂ + C₆H₅SO₂Cl → C₆H₅SO₂NHR (Soluble in alkali) + HCl",
                reactants = "Amine, Benzenesulfonyl chloride (Hinsberg Reagent)",
                products = "Sulfonamide",
                reagentsAndCatalyst = "Benzenesulfonyl chloride (C₆H₅SO₂Cl) + aqueous KOH",
                conditions = "Shaking at room temperature",
                mechanismType = "Nucleophilic Acyl Substitution at Sulfur",
                mechanismSteps = listOf(
                    "1° amine gives N-alkylbenzenesulfonamide containing acidic hydrogen, fully soluble in aqueous KOH.",
                    "2° amine gives N,N-dialkylbenzenesulfonamide without acidic hydrogen, insoluble precipitate in KOH.",
                    "3° amine does not react at all and remains insoluble oil."
                ),
                keyObservation = "1°: Precipitate dissolves in KOH; 2°: Precipitate stays insoluble in KOH; 3°: No reaction.",
                importance = "Industrial and educational benchmark to separate and distinguish amine mixtures."
            )
        ),
        identificationTests = listOf(
            OrganicTest(
                testName = "Carbylamine Test for 1° Amines",
                reagent = "Chloroform + Alcoholic KOH, heated gently",
                observation = "Overwhelmingly foul, nauseating odor of isocyanide.",
                explanation = "Only primary amines react with :CCl₂ to yield volatile isocyanides.",
                chemicalEquation = "R–NH₂ + CHCl₃ + 3KOH → R–NC (Foul odor) + 3KCl + 3H₂O"
            ),
            OrganicTest(
                testName = "Azo Dye Test for Aromatic Primary Amines (Aniline)",
                reagent = "NaNO₂ + Dilute HCl (0–5°C), then alkaline β-Naphthol solution",
                observation = "Brilliant, intense Scarlet / Orange-Red precipitate of Azo Dye.",
                explanation = "Diazotization forms benzene diazonium chloride, which couples with β-naphthol.",
                chemicalEquation = "C₆H₅N₂⁺Cl⁻ + C₁₀H₇O⁻ → Orange-Red Azo Dye ↓ + Cl⁻ + H₂O"
            )
        )
    )

    val amides = OrganicFunctionalGroup(
        id = "amides",
        name = "Amides",
        hindiName = "ऐमाइड (-CONH₂ समूह, पेप्टाइड आबंध)",
        category = OrganicCategory.NITROGEN_CONTAINING,
        generalFormula = "R–CO–NH₂ (1°), R–CO–NHR' (2°), R–CO–NR'₂ (3°)",
        condensedStructure = "–CONH₂",
        visualStructure2D = "      O\n      ||\n  R – C – NH₂",
        iupacSuffix = "-amide (e.g. ethanamide)",
        iupacPrefix = "carbamoyl- or amido-",
        priorityOrder = 9,
        hybridization = "Carbonyl carbon is sp²; nitrogen has significant sp² character due to strong resonance delocalization",
        bondAngle = "~120° (Planar C–N peptide bond with partial double bond character)",
        polarity = "Extremely Polar with high dipole moment (~3.7 D) and extensive hydrogen bonding network",
        boilingPointTrend = "Extremely high boiling and melting points (Ethanamide MP = 82°C, BP = 221°C); solid at room temperature.",
        solubility = "Lower amides are freely soluble in water.",
        acidityBasicity = "Neutral to very weakly basic (pKa of conjugate acid ~ -0.5); nitrogen lone pair is delocalized into carbonyl oxygen.",
        description = "Derivatives of carboxylic acids in which the hydroxyl is replaced by an amino group. The fundamental bond of all proteins, enzymes, and synthetic polyamides (Nylon).",
        iupacNamingRules = listOf(
            "1. Drop -oic acid from parent acid and add -amide (e.g., ethanoic acid → ethanamide).",
            "2. For substituted amides, prefix substituents on nitrogen with 'N-' (e.g., N-methylethanamide).",
            "3. If cyclic, use suffix -carboxamide."
        ),
        examples = listOf(
            OrganicExample("Methanamide", "Formamide", "CH₃NO", "HCONH₂", "H–CONH₂", "Liquid ionizing solvent for pharmaceuticals"),
            OrganicExample("Ethanamide", "Acetamide", "C₂H₅NO", "CH₃CONH₂", "CH₃–CONH₂", "Solid crystalline amide, plasticizer"),
            OrganicExample("N,N-Dimethylformamide", "DMF", "C₃H₇NO", "HCON(CH₃)₂", "H–CO–N(CH₃)₂", "Universal polar aprotic industrial solvent")
        ),
        reactions = listOf(
            OrganicReaction(
                id = "hoffmann_bromamide",
                name = "Hoffmann Bromamide Degradation",
                reactionType = OrganicReactionType.NAMED_REACTION,
                equation = "R–CONH₂ + Br₂ + 4NaOH → [Δ] R–NH₂ (Amine with 1 less C) + Na₂CO₃ + 2NaBr + 2H₂O",
                reactants = "Primary amide, Bromine, Sodium hydroxide",
                products = "Primary amine containing one fewer carbon atom",
                reagentsAndCatalyst = "Bromine (Br₂) + 4 equivalents of aqueous NaOH",
                conditions = "Gentle heating at 60–70°C",
                mechanismType = "Intramolecular Rearrangement via Isocyanate intermediate",
                mechanismSteps = listOf(
                    "Bromination of amide nitrogen forms N-bromoamide.",
                    "Base deprotonation gives bromoamide anion.",
                    "Concerted alkyl shift to nitrogen with departure of bromide yields isocyanate [R–N=C=O].",
                    "Rapid hydrolysis of isocyanate decarboxylates to yield primary amine."
                ),
                keyObservation = "Loss of one carbon atom and generation of volatile, basic primary amine",
                importance = "Classic 'step-down' reaction to shorten an organic carbon chain by exactly one carbon."
            )
        ),
        identificationTests = listOf(
            OrganicTest(
                testName = "Alkaline Hydrolysis (Ammonia Evolution)",
                reagent = "Aqueous NaOH, heated to boiling",
                observation = "Evolution of pungent ammonia gas (NH₃) turning moist red litmus paper blue.",
                explanation = "Amides hydrolyze in hot alkali to yield sodium carboxylate and free ammonia.",
                chemicalEquation = "R–CONH₂ + NaOH → [Δ] R–COONa + NH₃ ↑ (Turns red litmus blue)"
            ),
            OrganicTest(
                testName = "Biuret Test for Peptide Amide Linkages",
                reagent = "1% CuSO₄ in dilute NaOH",
                observation = "Spectacular Violet / Purple coloration.",
                explanation = "Cu²⁺ ions coordinate with four amide nitrogen atoms in an alkaline chelate complex.",
                chemicalEquation = "Peptide amides + Cu²⁺ + OH⁻ → Violet Coordination Complex"
            )
        )
    )

    val nitriles = OrganicFunctionalGroup(
        id = "nitriles",
        name = "Nitriles (Cyanides)",
        hindiName = "नाइट्राइल / सायनाइड (-C≡N समूह)",
        category = OrganicCategory.NITROGEN_CONTAINING,
        generalFormula = "R–C≡N",
        condensedStructure = "–CN",
        visualStructure2D = "  R – C ≡ N   (lone pair on N)",
        iupacSuffix = "-nitrile (e.g. ethanenitrile)",
        iupacPrefix = "cyano-",
        priorityOrder = 11,
        hybridization = "Both carbon and nitrogen atoms are sp hybridized",
        bondAngle = "180° (Linear cylindrical electron cloud)",
        polarity = "Highly Polar (large dipole moment ~ 3.9 D)",
        boilingPointTrend = "Significantly higher than alkanes of similar mass due to intense dipole-dipole attractions.",
        solubility = "Lower nitriles like acetonitrile are completely miscible with water.",
        acidityBasicity = "Alpha hydrogens are unusually acidic (pKa ~ 25) due to strong electron withdrawal by cyano group.",
        description = "Organic compounds having a cyano group (-C≡N) attached to a carbon skeleton. Key intermediates for introducing a carbon atom and converting to carboxylic acids or amines.",
        iupacNamingRules = listOf(
            "1. Suffix -nitrile is added to full parent alkane name (including nitrile carbon, e.g., propane → propanenitrile).",
            "2. The nitrile carbon is numbered as C-1 in the chain.",
            "3. If attached to a ring, use suffix -carbonitrile (e.g., benzenecarbonitrile)."
        ),
        examples = listOf(
            OrganicExample("Ethanenitrile", "Acetonitrile / Methyl Cyanide", "C₂H₃N", "CH₃CN", "CH₃–C≡N", "Premier HPLC mobile phase chromatography solvent"),
            OrganicExample("Propanenitrile", "Propionitrile", "C₃H₅N", "CH₃CH₂CN", "CH₃CH₂–C≡N", "Precursor to pharmaceuticals and dielectric fluid"),
            OrganicExample("Propenenitrile", "Acrylonitrile", "C₃H₃N", "CH₂=CHCN", "CH₂=CH–C≡N", "Major monomer for acrylic fibers (Orlon) and nitrile rubber")
        ),
        reactions = listOf(
            OrganicReaction(
                id = "stephen_reduction",
                name = "Stephen Reaction (Synthesis of Aldehydes)",
                reactionType = OrganicReactionType.NAMED_REACTION,
                equation = "R–CN + 2[H] → [SnCl₂ / HCl, then H₃O⁺] R–CHO + NH₄Cl",
                reactants = "Alkyl or aryl nitrile",
                products = "Aldehyde",
                reagentsAndCatalyst = "Stannous chloride (SnCl₂) + Concentrated HCl, followed by steam hydrolysis",
                conditions = "Room temperature reduction, followed by boiling water workup",
                mechanismType = "Reduction to Aldimine hydrochloride intermediate followed by hydrolysis",
                mechanismSteps = listOf(
                    "SnCl₂ + 2HCl generates active nascent reducing species.",
                    "Partial reduction of nitrile yields aldimine hydrochloride salt [R–CH=NH·HCl].",
                    "Hydrolysis with hot water cleaves C=N bond cleanly into aldehyde and ammonium salt."
                ),
                keyObservation = "Precipitation of aldimine salt which hydrolyzes to give fragrant aldehyde",
                importance = "Selective mild method to convert nitriles to aldehydes without over-reducing to alcohols."
            )
        ),
        identificationTests = listOf(
            OrganicTest(
                testName = "Complete Hydrolysis to Carboxylic Acid",
                reagent = "Boiling with 20% aqueous NaOH or H₂SO₄",
                observation = "Vigorous release of ammonia gas, followed by precipitation of carboxylic acid upon acidification.",
                explanation = "Nitriles completely hydrolyze via amide to carboxylic acids and ammonia.",
                chemicalEquation = "R–CN + 2H₂O + H⁺ → [Δ] R–COOH + NH₄⁺"
            )
        )
    )

    val nitroCompounds = OrganicFunctionalGroup(
        id = "nitro_compounds",
        name = "Nitro Compounds",
        hindiName = "नाइट्रो यौगिक (-NO₂ समूह)",
        category = OrganicCategory.NITROGEN_CONTAINING,
        generalFormula = "R–NO₂ (Ar–NO₂)",
        condensedStructure = "–NO₂",
        visualStructure2D = "        O\n       / \n  R – N⁺\n       \\\n        O⁻  (resonance hybrid)",
        iupacSuffix = "No suffix exists in IUPAC; ALWAYS named as prefix nitro-",
        iupacPrefix = "nitro- (e.g. nitromethane, nitrobenzene)",
        priorityOrder = 18,
        hybridization = "Nitrogen is sp² hybridized with planar geometry",
        bondAngle = "125° (O–N–O bond angle)",
        polarity = "Exceptionally Polar (dipole moment ~ 3.5–4.0 D)",
        boilingPointTrend = "High boiling points due to strong dipole-dipole interactions.",
        solubility = "Sparingly soluble in water, highly soluble in organic solvents.",
        acidityBasicity = "Alpha hydrogens are strongly acidic (pKa ~ 10) due to intense resonance stabilization of nitronate anion.",
        description = "Organic compounds containing one or more nitro groups (-NO₂). Potent electron-withdrawing groups (-I and -M effects). Crucial precursors to amines and explosives.",
        iupacNamingRules = listOf(
            "1. Always designated as a prefix 'nitro-' followed by parent alkane or arene.",
            "2. Example: CH₃NO₂ is Nitromethane; C₆H₅NO₂ is Nitrobenzene."
        ),
        examples = listOf(
            OrganicExample("Nitromethane", "Nitrocarbol", "CH₃NO₂", "CH₃NO₂", "CH₃–NO₂", "High-performance drag racing fuel additive and solvent"),
            OrganicExample("Nitrobenzene", "Oil of Mirbane", "C₆H₅NO₂", "C₆H₅NO₂", "C₆H₅–NO₂", "Bitter almond smelling pale yellow oil, precursor to aniline"),
            OrganicExample("2,4,6-Trinitrotoluene", "TNT", "C₇H₅N₃O₆", "CH₃C₆H₂(NO₂)₃", "CH₃–C₆H₂(NO₂)₃", "Standard military high explosive")
        ),
        reactions = listOf(
            OrganicReaction(
                id = "reduction_of_nitro_to_amine",
                name = "Reduction of Nitro to Primary Amine",
                reactionType = OrganicReactionType.CHEMICAL_PROPERTY,
                equation = "Ar–NO₂ + 6[H] → [Fe / Conc. HCl, then OH⁻] Ar–NH₂ + 2H₂O",
                reactants = "Nitrobenzene, Iron scraps, Hydrochloric acid",
                products = "Aniline (Benzenamine), Water",
                reagentsAndCatalyst = "Iron scrap (Fe) + Concentrated HCl (or Sn/HCl or catalytic H₂/Pd)",
                conditions = "Refluxing temperature",
                mechanismType = "Stepwise Multi-electron Surface Reduction",
                mechanismSteps = listOf(
                    "Nitro is reduced sequentially to nitroso (-NO), then hydroxylamine (-NHOH), and finally primary amine (-NH₂).",
                    "Fe + 2HCl produces active nascent hydrogen; FeCl₂ hydrolyzes regenerating HCl so only catalytic acid is needed."
                ),
                keyObservation = "Disappearance of yellow oily nitrobenzene and emergence of brown aniline oil",
                importance = "The premier industrial pathway to produce millions of tons of aniline worldwide."
            )
        ),
        identificationTests = listOf(
            OrganicTest(
                testName = "Mulliken-Barker Test for Nitro Groups",
                reagent = "Zinc dust + NH₄Cl, then Tollens' reagent",
                observation = "Instant formation of shiny Silver Mirror on tube walls.",
                explanation = "Zinc dust mildly reduces -NO₂ to hydroxylamine (-NHOH), which reduces Tollens' reagent.",
                chemicalEquation = "R–NO₂ + 4[H] → [Zn/NH₄Cl] R–NHOH | R–NHOH + 2[Ag(NH₃)₂]⁺ → Silver Mirror (Ag⁰)"
            )
        )
    )

    val haloalkanes = OrganicFunctionalGroup(
        id = "haloalkanes",
        name = "Haloalkanes (Alkyl Halides)",
        hindiName = "हैलोऐल्केन / ऐल्किल हैलाइड (R–X समूह)",
        category = OrganicCategory.HALOGENATED,
        generalFormula = "R–X (where X = F, Cl, Br, I)",
        condensedStructure = "–X",
        visualStructure2D = "      H\n      |\n  R – C – X   (polarized Cδ⁺–Xδ⁻)",
        iupacSuffix = "No suffix in IUPAC; ALWAYS named as prefix halo- (chloro-, bromo-, etc.)",
        iupacPrefix = "halo- (fluoro-, chloro-, bromo-, iodo-)",
        priorityOrder = 19,
        hybridization = "Carbon bearing halogen is sp³ hybridized",
        bondAngle = "109.5° (Tetrahedral)",
        polarity = "Polar due to halogen electronegativity (Cδ⁺–Xδ⁻)",
        boilingPointTrend = "RI > RBr > RCl > RF > RH; boiling point increases with increasing halogen atomic mass due to larger polarizable electron cloud and stronger London dispersion forces.",
        solubility = "Insoluble in water despite polarity (cannot form H-bonds with water); highly soluble in organic solvents.",
        acidityBasicity = "Neutral; carbon is electrophilic and highly susceptible to nucleophiles.",
        description = "Compounds in which one or more hydrogen atoms of an alkane are replaced by halogen atoms. The premier electrophilic substrates for organic synthesis.",
        iupacNamingRules = listOf(
            "1. Always prefixed with halo- (chloro-, bromo-, etc.).",
            "2. Number chain giving lowest possible locants to substituents.",
            "3. If multiple halogens present, list alphabetically (bromo- before chloro-)."
        ),
        examples = listOf(
            OrganicExample("Chloromethane", "Methyl Chloride", "CH₃Cl", "CH₃Cl", "CH₃–Cl", "Silicone polymer manufacturing chemical"),
            OrganicExample("Trichloromethane", "Chloroform", "CHCl₃", "CHCl₃", "HCCl₃", "Historical volatile anesthetic, Teflon precursor"),
            OrganicExample("2-Bromobutane", "sec-Butyl Bromide", "C₄H₉Br", "CH₃CH(Br)CH₂CH₃", "CH₃–CH(Br)–CH₂CH₃", "Chiral alkyl halide demonstrating stereospecific SN2 inversion")
        ),
        reactions = listOf(
            OrganicReaction(
                id = "bimolecular_nucleophilic_substitution_sn2",
                name = "SN2 Mechanism (Walden Inversion)",
                reactionType = OrganicReactionType.CHEMICAL_PROPERTY,
                equation = "CH₃–Br + OH⁻ → [Water/Acetone] CH₃–OH + Br⁻",
                reactants = "Methyl bromide, Hydroxide ion nucleophile",
                products = "Methanol, Bromide leaving group",
                reagentsAndCatalyst = "Strong nucleophile in polar aprotic solvent (e.g., DMSO, acetone)",
                conditions = "Room temperature, single concerted step",
                mechanismType = "Bimolecular Nucleophilic Substitution (SN2)",
                mechanismSteps = listOf(
                    "Backside attack of nucleophile at 180° relative to leaving group.",
                    "Pentacoordinate trigonal bipyramidal transition state with partial C···Nu and C···LG bonds.",
                    "Simultaneous departure of leaving group accompanied by 100% inversion of stereochemical configuration (Walden Inversion)."
                ),
                keyObservation = "Second-order rate: Rate = k[R-X][Nu⁻], complete stereochemical inversion",
                importance = "The cornerstone reaction for stereospecific organic synthesis."
            ),
            OrganicReaction(
                id = "grignard_formation",
                name = "Grignard Reagent Synthesis",
                reactionType = OrganicReactionType.NAMED_REACTION,
                equation = "R–X + Mg → [Dry Ether / THF] R–Mg–X (Grignard Reagent)",
                reactants = "Alkyl or aryl halide, Magnesium turnings",
                products = "Organomagnesium halide (Grignard Reagent)",
                reagentsAndCatalyst = "Pure Magnesium turnings in anhydrous diethyl ether or THF",
                conditions = "Strictly anhydrous, inert nitrogen/argon atmosphere",
                mechanismType = "Single Electron Transfer (SET) at Magnesium metal surface",
                mechanismSteps = listOf(
                    "Electron transfer from Mg surface to alkyl halide produces radical anion [R–X]•⁻.",
                    "Rapid cleavage forms R• radical and [MgX]•.",
                    "Recombination delivers organomagnesium halide with highly polarized nucleophilic carbanionic carbon (Cδ⁻–Mgδ⁺)."
                ),
                keyObservation = "Magnesium metal dissolves with vigorous boiling of dry ether",
                importance = "Nobel Prize winning carbon-carbon bond constructing tool in organic chemistry."
            )
        ),
        identificationTests = listOf(
            OrganicTest(
                testName = "Beilstein Flame Test (Halogen Detection)",
                reagent = "Clean copper wire heated in Bunsen flame",
                observation = "Flame glows with a spectacular bright Green / Bluish-Green color.",
                explanation = "Halogen reacts with copper to form volatile copper halide vapor that tints flame green.",
                chemicalEquation = "R–X + Cu → [Flame] CuX₂ (Volatile copper halide, bright green flame)"
            ),
            OrganicTest(
                testName = "Silver Nitrate Test in Ethanol",
                reagent = "Warm ethanolic AgNO₃ solution",
                observation = "RCl: White ppt soluble in NH₄OH; RBr: Pale yellow ppt sparingly soluble; RI: Bright yellow ppt insoluble.",
                explanation = "Halide ion is displaced by solvent and precipitates with Ag⁺.",
                chemicalEquation = "R–X + AgNO₃ → AgX ↓ (Precipitate) + R–NO₃"
            )
        )
    )

    val thiols = OrganicFunctionalGroup(
        id = "thiols",
        name = "Thiols (Mercaptans)",
        hindiName = "थायॉल / मर्केप्टान (-SH समूह)",
        category = OrganicCategory.SULFUR_CONTAINING,
        generalFormula = "R–SH",
        condensedStructure = "–SH",
        visualStructure2D = "  R – S – H",
        iupacSuffix = "-thiol (e.g. ethanethiol)",
        iupacPrefix = "sulfanyl- or mercapto-",
        priorityOrder = 12,
        hybridization = "Sulfur is sp³ hybridized",
        bondAngle = "96.5° (Closer to unhybridized 90° p-orbitals than oxygen)",
        polarity = "Weakly Polar; lacks strong hydrogen bonding (S is less electronegative than O)",
        boilingPointTrend = "Much lower than corresponding alcohols (Ethanethiol BP = 35°C vs Ethanol BP = 78°C) due to absence of strong hydrogen bonding.",
        solubility = "Insoluble in water, soluble in organic solvents.",
        acidityBasicity = "Significantly more acidic than alcohols (pKa ~ 10 vs 16) due to larger polarizable sulfur atom stabilizing thiolate anion (RS⁻).",
        description = "Sulfur analogues of alcohols containing a sulfhydryl (-SH) group. Renowned for extremely potent, pungent garlic/skunk odors. Critical in protein tertiary folding via disulfide bonds (–S–S–).",
        iupacNamingRules = listOf(
            "1. Suffix -thiol is added to parent alkane name (e.g., methanethiol, ethanethiol).",
            "2. As a substituent, use the prefix 'sulfanyl-'."
        ),
        examples = listOf(
            OrganicExample("Methanethiol", "Methyl Mercaptan", "CH₄S", "CH₃SH", "CH₃–SH", "Natural gas odorant warning agent and cabbage aroma"),
            OrganicExample("Ethanethiol", "Ethyl Mercaptan", "C₂H₆S", "CH₃CH₂SH", "CH₃–CH₂–SH", "Added to LPG gas cylinders (cooking gas) to provide detectable odor for leak detection"),
            OrganicExample("Cysteine", "2-Amino-3-mercaptopropanoic acid", "C₃H₇NO₂S", "HSCH₂CH(NH₂)COOH", "HS–CH₂–CH(NH₂)–COOH", "Proteinogenic amino acid forming disulfide bridges in insulin and keratin")
        ),
        reactions = listOf(
            OrganicReaction(
                id = "disulfide_bond_formation",
                name = "Oxidative Disulfide Bond Formation",
                reactionType = OrganicReactionType.CHEMICAL_PROPERTY,
                equation = "2R–SH + [O] → [I₂ / mild oxidant] R–S–S–R (Disulfide) + H₂O",
                reactants = "Two thiol molecules, Mild oxidant",
                products = "Disulfide compound, Water",
                reagentsAndCatalyst = "Iodine (I₂) or atmospheric oxygen / Fe³⁺",
                conditions = "Neutral to mild alkaline aqueous buffer",
                mechanismType = "Two-electron oxidation with radical or sulfenyl iodide intermediate",
                mechanismSteps = listOf(
                    "Thiolate anion attacks iodine forming sulfenyl iodide intermediate [R–S–I].",
                    "Second thiolate anion nucleophilically displaces iodide to generate covalent disulfide bridge (R–S–S–R)."
                ),
                keyObservation = "Loss of free thiol functionality and stabilization of macrocycle/protein",
                importance = "The essential structural cross-link holding all mammalian proteins and antibodies in native 3D shape."
            )
        ),
        identificationTests = listOf(
            OrganicTest(
                testName = "Lead Acetate Test for Thiols",
                reagent = "Aqueous Lead(II) acetate (Pb(CH₃COO)₂) + NaOH",
                observation = "Immediate dense, jet-black precipitate of Lead(II) sulfide (PbS).",
                explanation = "Mercaptan captures heavy metal forming insoluble black lead sulfide.",
                chemicalEquation = "2R–SH + Pb²⁺ + 2OH⁻ → PbS ↓ (Jet Black ppt) + R–S–R + 2H₂O"
            )
        )
    )

    val sulfonicAcids = OrganicFunctionalGroup(
        id = "sulfonic_acids",
        name = "Sulfonic Acids",
        hindiName = "सल्फोनिक अम्ल (-SO₃H समूह)",
        category = OrganicCategory.SULFUR_CONTAINING,
        generalFormula = "R–SO₃H (Ar–SO₃H)",
        condensedStructure = "–SO₃H",
        visualStructure2D = "        O\n        ||\n  R – S = O\n        |\n        O – H",
        iupacSuffix = "-sulfonic acid (e.g. benzenesulfonic acid)",
        iupacPrefix = "sulfo-",
        priorityOrder = 2, // Second only to Carboxylic acids in IUPAC seniority!
        hybridization = "Sulfur has expanded octet with sp³ tetrahedral framework",
        bondAngle = "~109.5°",
        polarity = "Exceptionally Polar and strongly ionic in solution",
        boilingPointTrend = "Extremely high boiling points, generally non-volatile hygroscopic solids.",
        solubility = "Freely soluble in water with almost complete ionization.",
        acidityBasicity = "Extremely strong organic acids (pKa ~ -2.8 for p-toluenesulfonic acid), comparable in strength to sulfuric acid due to resonance delocalization over three equivalent oxygen atoms.",
        description = "Organosulfur compounds containing the sulfonic acid group (-SO₃H). Widely used as strong acid catalysts (PTSA / TsOH) and synthetic detergents (LAS).",
        iupacNamingRules = listOf(
            "1. Suffix -sulfonic acid is appended directly to parent hydrocarbon name (e.g., benzenesulfonic acid).",
            "2. As priority #2 in IUPAC table, it outranks all groups except carboxylic acids."
        ),
        examples = listOf(
            OrganicExample("Benzenesulfonic acid", "Besylic Acid", "C₆H₆O₃S", "C₆H₅SO₃H", "C₆H₅–SO₃H", "Parent aromatic sulfonic acid, precursor to dyes and phenols"),
            OrganicExample("4-Methylbenzenesulfonic acid", "p-Toluenesulfonic Acid (TsOH / PTSA)", "C₇H₈O₃S", "CH₃C₆H₄SO₃H", "p-CH₃–C₆H₄–SO₃H", "Convenient crystalline solid acid catalyst for organic synthesis and esterification"),
            OrganicExample("Sodium dodecylbenzenesulfonate", "Linear Alkylbenzene Sulfonate (LAS)", "C₁₈H₂₉NaO₃S", "C₁₂H₂₅C₆H₄SO₃Na", "C₁₂H₂₅–C₆H₄–SO₃Na", "World's most widely consumed synthetic laundry detergent active ingredient")
        ),
        reactions = listOf(
            OrganicReaction(
                id = "tosylation_reaction",
                name = "Tosylation of Alcohols (Activation as Leaving Group)",
                reactionType = OrganicReactionType.NAMED_REACTION,
                equation = "R–OH + TsCl → [Pyridine, 0°C] R–OTs (Alkyl Tosylate) + Py·HCl",
                reactants = "Alcohol, p-Toluenesulfonyl chloride (TsCl)",
                products = "Alkyl Tosylate (Superb leaving group), Pyridinium chloride",
                reagentsAndCatalyst = "p-Toluenesulfonyl chloride (TsCl) in dry pyridine",
                conditions = "Ice bath (0–5°C)",
                mechanismType = "Nucleophilic Acyl-like Substitution at Sulfur with Retention of Carbon Configuration",
                mechanismSteps = listOf(
                    "Alcohol oxygen attacks electrophilic sulfur atom of TsCl.",
                    "Chloride ion departs and pyridine scavenges liberated proton.",
                    "Crucial feature: C–O bond is never broken, so absolute stereochemical configuration of chiral carbon is 100% preserved while converting poor –OH into magnificent –OTs leaving group."
                ),
                keyObservation = "Precipitation of pyridinium hydrochloride needles and formation of alkyl tosylate",
                importance = "The standard chemical tool to transform unreactive alcohols into powerful substrates for SN2 substitutions."
            )
        ),
        identificationTests = listOf(
            OrganicTest(
                testName = "Barium Chloride Test for Sulfonic Acids",
                reagent = "Fused with Na₂CO₃/KNO₃, extracted with water, then acidified with dilute HCl and BaCl₂ added",
                observation = "Heavy, white precipitate of Barium Sulfate (BaSO₄) completely insoluble in concentrated acids.",
                explanation = "Oxidative fusion converts sulfur into sulfate ions (SO₄²⁻) which precipitate with barium.",
                chemicalEquation = "SO₄²⁻ + BaCl₂ → BaSO₄ ↓ (Insoluble white ppt) + 2Cl⁻"
            )
        )
    )
}
