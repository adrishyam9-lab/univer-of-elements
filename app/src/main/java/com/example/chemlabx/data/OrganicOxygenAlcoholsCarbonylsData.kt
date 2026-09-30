package com.example.chemlabx.data

import com.example.chemlabx.data.model.*

object OrganicOxygenAlcoholsCarbonylsData {

    val alcohols = OrganicFunctionalGroup(
        id = "alcohols",
        name = "Alcohols",
        hindiName = "ऐल्कोहॉल (-OH समूह)",
        category = OrganicCategory.OXYGEN_CONTAINING,
        generalFormula = "R–OH (CnH2n+1OH)",
        condensedStructure = "–OH",
        visualStructure2D = "  R – O – H",
        iupacSuffix = "-ol (e.g. ethanol, propan-2-ol)",
        iupacPrefix = "hydroxy-",
        priorityOrder = 8,
        hybridization = "Oxygen is sp³ hybridized with two lone pairs",
        bondAngle = "104.5° (Bent / V-shaped due to lone pair-lone pair repulsion)",
        polarity = "Strongly Polar, robust intermolecular hydrogen bonding",
        boilingPointTrend = "Significantly higher boiling point than corresponding alkanes, ethers, or alkyl halides due to strong intermolecular O–H···O hydrogen bonding.",
        solubility = "C1–C3 alcohols are miscible with water in all proportions; solubility gradually decreases as hydrophobic alkyl chain lengthens.",
        acidityBasicity = "Amphiprotic; weakly acidic (pKa ~ 16–18), slightly weaker acid than water.",
        description = "Organic compounds having a hydroxyl group (-OH) covalently attached to a saturated sp³ carbon atom. Classified as 1°, 2°, or 3° based on carbon substitution.",
        iupacNamingRules = listOf(
            "1. Longest chain must contain the carbon bearing the -OH group.",
            "2. Number chain from the end closer to -OH (hydroxyl gets lower locant than double/triple bonds and halides).",
            "3. Replace terminal -e with -ol (e.g., propane → propan-1-ol).",
            "4. For polyhydric alcohols, use -diol, -triol with position numbers (e.g., ethane-1,2-diol)."
        ),
        examples = listOf(
            OrganicExample("Methanol", "Wood Alcohol / Spirit", "CH₄O", "CH₃OH", "CH₃–OH", "Highly toxic industrial solvent; causes blindness if ingested"),
            OrganicExample("Ethanol", "Grain Alcohol / Alcohol", "C₂H₆O", "CH₃CH₂OH", "CH₃–CH₂–OH", "Universal solvent, antiseptic, alcoholic beverage active ingredient, biofuel"),
            OrganicExample("Propan-2-ol", "Isopropyl Alcohol (IPA)", "C₃H₈O", "CH₃CH(OH)CH₃", "(CH₃)₂CH–OH", "Standard rubbing alcohol and electronics disinfectant"),
            OrganicExample("Propane-1,2,3-triol", "Glycerol / Glycerin", "C₃H₈O₃", "CH₂(OH)CH(OH)CH₂(OH)", "CH₂(OH)–CH(OH)–CH₂(OH)", "Triol backbone of lipids and triglycerides, humectant")
        ),
        reactions = listOf(
            OrganicReaction(
                id = "alcohol_oxidation",
                name = "Oxidation of Alcohols",
                reactionType = OrganicReactionType.CHEMICAL_PROPERTY,
                equation = "CH₃CH₂OH + [O] → [PCC] CH₃CHO (Aldehyde) | → [Acidic KMnO₄] CH₃COOH",
                reactants = "Primary alcohol, Oxidizing agent",
                products = "Aldehyde (with PCC) or Carboxylic acid (with Jones/KMnO₄)",
                reagentsAndCatalyst = "Pyridinium Chlorochromate (PCC) for aldehyde stop; Alkaline KMnO₄ or K₂Cr₂O₇/H₂SO₄ for full oxidation",
                conditions = "Anhydrous CH₂Cl₂ (for PCC); reflux in aqueous acid (for Jones)",
                mechanismType = "Hydride transfer and elimination of chromate ester intermediate",
                mechanismSteps = listOf(
                    "Formation of chromate ester between alcohol oxygen and chromium(VI).",
                    "Base-assisted elimination of proton and departure of reduced Cr(IV) species to yield carbonyl."
                ),
                keyObservation = "Orange potassium dichromate turns deep green (Cr³⁺ reduction)",
                importance = "Indispensable method to synthesize aldehydes, ketones, and carboxylic acids."
            ),
            OrganicReaction(
                id = "lucas_test_reaction",
                name = "Lucas Test (Distinction of 1°, 2°, 3° Alcohols)",
                reactionType = OrganicReactionType.TEST_IDENTIFICATION,
                equation = "R–OH + HCl → [Anhydrous ZnCl₂] R–Cl ↓ (Turbidity) + H₂O",
                reactants = "Alcohol, Concentrated HCl",
                products = "Alkyl chloride (insoluble oil droplets), Water",
                reagentsAndCatalyst = "Lucas Reagent: Equimolar Anhydrous ZnCl₂ + Conc. HCl",
                conditions = "Room temperature",
                mechanismType = "Nucleophilic Substitution (SN1 for 3°/2°, SN2 for 1°)",
                mechanismSteps = listOf(
                    "ZnCl₂ coordinates with oxygen lone pair making it a superior leaving group.",
                    "3° alcohols form stable 3° carbocations instantly yielding insoluble RCl oil drops.",
                    "2° alcohols react within 5 minutes upon gentle warming.",
                    "1° alcohols do not form turbidity at room temperature."
                ),
                keyObservation = "3° alcohol: Instant cloudiness/turbidity; 2° alcohol: Turbidity in 5 mins; 1°: Clear at room temp.",
                importance = "Classic qualitative laboratory test to distinguish primary, secondary, and tertiary alcohols."
            )
        ),
        identificationTests = listOf(
            OrganicTest(
                testName = "Lucas Test",
                reagent = "Anhydrous ZnCl₂ + Conc. HCl (Lucas Reagent)",
                observation = "3°: Instant oily turbidity; 2°: Turbidity within 5 minutes; 1°: Solution remains clear.",
                explanation = "Carbocation stability (3° > 2° > 1°) governs rate of conversion to insoluble alkyl chloride.",
                chemicalEquation = "R–OH + HCl → [ZnCl₂] R–Cl (insoluble oily layer) + H₂O"
            ),
            OrganicTest(
                testName = "Sodium Metal Effervescence Test",
                reagent = "Dry Sodium metal slice",
                observation = "Brisk effervescence of colorless Hydrogen gas (H₂) which burns with a 'pop' sound.",
                explanation = "Active sodium displaces acidic hydroxyl proton forming sodium alkoxide.",
                chemicalEquation = "2R–OH + 2Na → 2R–ONa + H₂ ↑ (Brisk effervescence)"
            ),
            OrganicTest(
                testName = "Iodoform Test (for Ethanol & 2° Methyl Alcohols)",
                reagent = "I₂ in aqueous NaOH (Sodium hypoiodite)",
                observation = "Bright yellow crystalline precipitate with characteristic medicinal antiseptic odor.",
                explanation = "Compounds containing the CH₃–CH(OH)– group undergo oxidation and iodination to produce CHI₃.",
                chemicalEquation = "CH₃CH₂OH + 4I₂ + 6NaOH → CHI₃ ↓ (Yellow ppt) + HCOONa + 5NaI + 5H₂O"
            )
        )
    )

    val phenols = OrganicFunctionalGroup(
        id = "phenols",
        name = "Phenols",
        hindiName = "फीनॉल (ऐरोमैटिक हाइड्रोक्सी यौगिक)",
        category = OrganicCategory.OXYGEN_CONTAINING,
        generalFormula = "Ar–OH (C₆H₅OH)",
        condensedStructure = "C₆H₅–OH",
        visualStructure2D = "  C₆H₅ – O – H",
        iupacSuffix = "-phenol",
        iupacPrefix = "hydroxy-",
        priorityOrder = 7,
        hybridization = "sp² carbon attached to sp²-sp³ hybridized oxygen",
        bondAngle = "109° (C–O–H angle)",
        polarity = "Strongly Polar, powerful hydrogen bonding donor/acceptor",
        boilingPointTrend = "High boiling point (182°C for phenol) due to intermolecular hydrogen bonding.",
        solubility = "Sparingly soluble in cold water; completely soluble in alkaline aqueous solutions due to phenoxide salt formation.",
        acidityBasicity = "Significantly more acidic than alcohols (pKa ~ 10 vs 16) because phenoxide ion is resonance-stabilized by benzene ring.",
        description = "Aromatic compounds containing one or more hydroxyl (-OH) groups directly bonded to an sp² hybridized carbon of an aromatic ring.",
        iupacNamingRules = listOf(
            "1. Phenol is recognized as the IUPAC parent name.",
            "2. Substituents are assigned numbers starting with the -OH bearing carbon as C-1.",
            "3. Common designations ortho (2-), meta (3-), and para (4-) are extensively used."
        ),
        examples = listOf(
            OrganicExample("Phenol", "Carbolic Acid", "C₆H₆O", "C₆H₅OH", "C₆H₅–OH", "Pioneer antiseptic introduced by Joseph Lister; precursor to aspirin and Bakelite"),
            OrganicExample("2-Methylphenol", "o-Cresol", "C₇H₈O", "o-CH₃–C₆H₄–OH", "2-CH₃–C₆H₄–OH", "Disinfectant component in Lysol"),
            OrganicExample("Benzene-1,4-diol", "Hydroquinone", "C₆H₆O₂", "p-C₆H₄(OH)₂", "HO–C₆H₄–OH", "Standard photographic reducing developer and antioxidant")
        ),
        reactions = listOf(
            OrganicReaction(
                id = "reimer_tiemann",
                name = "Reimer-Tiemann Reaction",
                reactionType = OrganicReactionType.NAMED_REACTION,
                equation = "C₆H₅OH + CHCl₃ + 3NaOH → [60°C, then H⁺] o-C₆H₄(OH)(CHO) (Salicylaldehyde) + 3NaCl + 2H₂O",
                reactants = "Phenol, Chloroform, Sodium Hydroxide",
                products = "Salicylaldehyde (2-Hydroxybenzaldehyde)",
                reagentsAndCatalyst = "Chloroform (CHCl₃) + aqueous NaOH",
                conditions = "Heating at 60°C, followed by acidic acidification",
                mechanismType = "Electrophilic Aromatic Substitution via Dichlorocarbene (:CCl₂)",
                mechanismSteps = listOf(
                    "Alpha-elimination of HCl from chloroform produces neutral reactive electrophile dichlorocarbene (:CCl₂).",
                    "Phenoxide ion attacks :CCl₂ at ortho position forming a benzylidene chloride intermediate.",
                    "Hydrolysis of gem-dichloride yielding salicylaldehyde."
                ),
                keyObservation = "Emergence of almond/fragrant medicinal odor of salicylaldehyde",
                importance = "Elegant direct method for introducing formyl (-CHO) group ortho to phenolic hydroxyl."
            ),
            OrganicReaction(
                id = "kolbe_schmitt",
                name = "Kolbe's Reaction (Synthesis of Aspirin Precursor)",
                reactionType = OrganicReactionType.NAMED_REACTION,
                equation = "C₆H₅ONa + CO₂ → [4-7 atm, 400 K, then H⁺] o-C₆H₄(OH)(COOH) (Salicylic Acid)",
                reactants = "Sodium phenoxide, Carbon dioxide",
                products = "Salicylic acid (2-Hydroxybenzoic acid)",
                reagentsAndCatalyst = "CO₂ under 4-7 atmospheric pressure + heat",
                conditions = "400 K (125°C), acidic workup",
                mechanismType = "Electrophilic Aromatic Substitution",
                mechanismSteps = listOf(
                    "Strongly nucleophilic phenoxide ring attacks electrophilic carbon of CO₂.",
                    "Proton rearrangement and re-aromatization yield sodium salicylate.",
                    "Acidification yields salicylic acid, the direct starting precursor to Aspirin."
                ),
                keyObservation = "Conversion of phenoxide into crystalline white needles of salicylic acid",
                importance = "Industrial gateway for global manufacture of acetylsalicylic acid (Aspirin)."
            )
        ),
        identificationTests = listOf(
            OrganicTest(
                testName = "Neutral Ferric Chloride Test (FeCl₃)",
                reagent = "Freshly prepared neutral FeCl₃ solution",
                observation = "Intense characteristic Violet / Purple / Blue coloration.",
                explanation = "Formation of iron(III)-phenolate coordination complex [Fe(OC₆H₅)₆]³⁻.",
                chemicalEquation = "6C₆H₅OH + FeCl₃ → [Fe(OC₆H₅)₆]³⁻ (Violet Complex) + 3H⁺ + 3Cl⁻"
            ),
            OrganicTest(
                testName = "Bromine Water Test for Phenol",
                reagent = "Aqueous Bromine water",
                observation = "Decolorization of bromine and immediate formation of a White Precipitate with antiseptic odor.",
                explanation = "Hydroxyl group strongly activates ring, giving polyhalogenation (2,4,6-tribromophenol).",
                chemicalEquation = "C₆H₅OH + 3Br₂ (aq) → 2,4,6-Tribromophenol ↓ (White ppt) + 3HBr"
            )
        )
    )

    val ethers = OrganicFunctionalGroup(
        id = "ethers",
        name = "Ethers",
        hindiName = "ईथर (R–O–R')",
        category = OrganicCategory.OXYGEN_CONTAINING,
        generalFormula = "R–O–R'",
        condensedStructure = "–O–",
        visualStructure2D = "  R – O – R'",
        iupacSuffix = "alkoxyalkane (e.g. methoxymethane)",
        iupacPrefix = "alkoxy- (e.g. methoxy-, ethoxy-)",
        priorityOrder = 17,
        hybridization = "sp³ hybridized oxygen with two lone pairs",
        bondAngle = "111.7° (Slightly larger than tetrahedral due to steric repulsion between bulky R groups)",
        polarity = "Weakly Polar (C–O bond dipole does not cancel)",
        boilingPointTrend = "Much lower than isomeric alcohols (diethyl ether BP = 34.6°C vs butan-1-ol BP = 117.7°C) because ethers lack intermolecular hydrogen bonding.",
        solubility = "Moderately soluble in water (can accept hydrogen bonds from water H–O–H); highly miscible with organic solvents.",
        acidityBasicity = "Lewis bases; oxygen lone pairs readily coordinate with Lewis acids (BF₃, Grignard reagents).",
        description = "Organic compounds having an oxygen atom bonded to two alkyl or aryl groups. Chemically inert and widely utilized as aprotic reaction media.",
        iupacNamingRules = listOf(
            "1. Choose the longer carbon chain as the parent alkane.",
            "2. The smaller alkyl group with the oxygen is named as an 'alkoxy' substituent.",
            "3. Number the parent chain giving alkoxy substituent the lowest locant (e.g., 2-methoxypropane)."
        ),
        examples = listOf(
            OrganicExample("Ethoxyethane", "Diethyl Ether", "C₄H₁₀O", "CH₃CH₂OCH₂CH₃", "CH₃CH₂–O–CH₂CH₃", "Historic general inhalation anesthetic and laboratory extraction solvent"),
            OrganicExample("Methoxymethane", "Dimethyl Ether", "C₂H₆O", "CH₃OCH₃", "CH₃–O–CH₃", "Clean-burning aerosol propellant and alternative diesel fuel"),
            OrganicExample("Methoxybenzene", "Anisole", "C₇H₈O", "C₆H₅OCH₃", "C₆H₅–O–CH₃", "Natural fragrance in anise seed, precursor to perfumes and pharmaceuticals")
        ),
        reactions = listOf(
            OrganicReaction(
                id = "williamson_ether_synthesis",
                name = "Williamson Ether Synthesis",
                reactionType = OrganicReactionType.NAMED_REACTION,
                equation = "R–ONa + R'–X → R–O–R' + NaX",
                reactants = "Sodium alkoxide, Primary alkyl halide",
                products = "Ether, Sodium halide",
                reagentsAndCatalyst = "Sodium metal, Dry alcohol, Primary alkyl halide (CH₃X or 1° R–X)",
                conditions = "Reflux, anhydrous conditions",
                mechanismType = "Bimolecular Nucleophilic Substitution (SN2)",
                mechanismSteps = listOf(
                    "Alkoxide ion acts as a strong nucleophile and attacks the backside of the primary alkyl halide carbon.",
                    "Simultaneous departure of halide leaving group via pentacoordinate transition state.",
                    "Note: If 3° alkyl halide is used, E2 elimination dominates yielding alkene instead of ether."
                ),
                keyObservation = "Precipitation of sodium halide and formation of distinct organic ether layer",
                importance = "Most versatile laboratory method for preparing symmetrical and unsymmetrical ethers."
            ),
            OrganicReaction(
                id = "cleavage_of_ethers_hi",
                name = "Cleavage of Ethers by Excess HI",
                reactionType = OrganicReactionType.CHEMICAL_PROPERTY,
                equation = "R–O–R' + 2HI → [Δ] R–I + R'–I + H₂O",
                reactants = "Ether, Concentrated Hydroiodic Acid",
                products = "Alkyl iodides, Water",
                reagentsAndCatalyst = "Excess Concentrated HI (57%) or HBr (48%)",
                conditions = "Boiling under reflux at 130°C",
                mechanismType = "Protonation followed by SN2 / SN1 nucleophilic attack by I⁻",
                mechanismSteps = listOf(
                    "Protonation of ether oxygen produces oxonium ion intermediate.",
                    "Iodide ion attacks less hindered alkyl group to yield alcohol and alkyl iodide.",
                    "With excess HI, the alcohol is further converted into second molecule of alkyl iodide."
                ),
                keyObservation = "Complete cleavage of chemically inert ether into alkyl iodides",
                importance = "Standard diagnostic reaction for structure determination and deprotection of ethers."
            )
        ),
        identificationTests = listOf(
            OrganicTest(
                testName = "Peroxide Test for Ethers",
                reagent = "Aqueous Ferrous Ammonium Sulfate (FeSO₄) + Potassium Thiocyanate (KCNS)",
                observation = "Blood-red coloration indicates hazardous presence of explosive organic peroxides.",
                explanation = "Ethers on prolonged exposure to air form peroxides that oxidize Fe²⁺ to Fe³⁺, forming [Fe(SCN)]²⁺.",
                chemicalEquation = "R–O–O–R + 2Fe²⁺ + 2H⁺ → 2Fe³⁺ + 2ROH | Fe³⁺ + SCN⁻ → [Fe(SCN)]²⁺ (Blood Red)"
            )
        )
    )

    val aldehydes = OrganicFunctionalGroup(
        id = "aldehydes",
        name = "Aldehydes",
        hindiName = "ऐल्डिहाइड (-CHO समूह)",
        category = OrganicCategory.OXYGEN_CONTAINING,
        generalFormula = "R–CHO",
        condensedStructure = "–CHO",
        visualStructure2D = "      O\n      ||\n  R – C – H",
        iupacSuffix = "-al (e.g. ethanal, propanal)",
        iupacPrefix = "formyl- or oxo-",
        priorityOrder = 5,
        hybridization = "Carbonyl carbon is sp² hybridized",
        bondAngle = "~120° (Trigonal Planar carbonyl group)",
        polarity = "Strongly Polar due to high electronegativity difference of C=O (dipole moment ~ 2.5–2.8 D)",
        boilingPointTrend = "Higher than alkanes and ethers of similar molecular weight, but lower than alcohols due to absence of intermolecular H-bonding.",
        solubility = "Formaldehyde and acetaldehyde are infinitely miscible with water; solubility diminishes past C4.",
        acidityBasicity = "Alpha-hydrogens (α-H) are notably acidic (pKa ~ 17) due to resonance stabilization of enolate ion.",
        description = "Carbonyl compounds containing a C=O bond with at least one hydrogen atom bonded directly to the carbonyl carbon. Highly prone to nucleophilic addition.",
        iupacNamingRules = listOf(
            "1. Longest chain must contain the -CHO carbon.",
            "2. The carbonyl carbon of the aldehyde is always numbered C-1 (locant 1 is omitted).",
            "3. Replace suffix -e of parent alkane with -al.",
            "4. For cyclic systems where -CHO is attached to ring, use suffix -carbaldehyde (e.g., cyclohexanecarbaldehyde)."
        ),
        examples = listOf(
            OrganicExample("Methanal", "Formaldehyde / Formalin", "CH₂O", "HCHO", "H–CHO", "Biological tissue preservative (37% formalin), Bakelite precursor"),
            OrganicExample("Ethanal", "Acetaldehyde", "C₂H₄O", "CH₃CHO", "CH₃–CHO", "Intermediate in ethanol metabolism, manufacturing of acetic acid and pyridines"),
            OrganicExample("Benzaldehyde", "Oil of Bitter Almond", "C₇H₆O", "C₆H₅CHO", "C₆H₅–CHO", "Aromatic aldehyde giving characteristic almond aroma, dye precursor")
        ),
        reactions = listOf(
            OrganicReaction(
                id = "aldol_condensation",
                name = "Aldol Condensation",
                reactionType = OrganicReactionType.NAMED_REACTION,
                equation = "2CH₃CHO → [Dilute NaOH, 273–283 K] CH₃–CH(OH)–CH₂–CHO → [Δ, -H₂O] CH₃–CH=CH–CHO (Crotonaldehyde)",
                reactants = "Two molecules of Ethanal (bearing α-hydrogens)",
                products = "3-Hydroxybutanal (Aldol), then But-2-enal (α,β-unsaturated aldehyde)",
                reagentsAndCatalyst = "Dilute base (10% NaOH or Ba(OH)₂)",
                conditions = "Mild cold temperature, followed by dehydration upon warming",
                mechanismType = "Nucleophilic Carbonyl Addition followed by E1cB Elimination",
                mechanismSteps = listOf(
                    "Deprotonation of acidic α-hydrogen by hydroxide yields resonance-stabilized carbanion (enolate).",
                    "Enolate attacks carbonyl carbon of second aldehyde molecule forming tetrahedral alkoxide.",
                    "Protonation from water affords β-hydroxyaldehyde (Aldol).",
                    "Spontaneous or heat-promoted dehydration via conjugated enone formation."
                ),
                keyObservation = "Condensation yields yellow-orange conjugated liquid with sharp pungent odor",
                importance = "Premier carbon-carbon bond forming reaction in organic synthesis and biochemistry."
            ),
            OrganicReaction(
                id = "cannizzaro_reaction",
                name = "Cannizzaro Reaction",
                reactionType = OrganicReactionType.NAMED_REACTION,
                equation = "2HCHO + Conc. NaOH → HCOONa (Sodium Formate) + CH₃OH (Methanol)",
                reactants = "Aldehydes lacking α-hydrogens (e.g. Formaldehyde, Benzaldehyde)",
                products = "Carboxylic acid salt + Primary Alcohol",
                reagentsAndCatalyst = "50% Concentrated NaOH or KOH",
                conditions = "Room temperature or gentle heating",
                mechanismType = "Self Oxidation-Reduction (Disproportionation) via Hydride Transfer",
                mechanismSteps = listOf(
                    "Hydroxide ion attacks carbonyl carbon of HCHO to give dianionic or monoanionic adduct.",
                    "Direct intermolecular hydride ion (:H⁻) shift to second molecule of aldehyde.",
                    "Proton exchange between carboxylic acid and alkoxide yields final salt and alcohol."
                ),
                keyObservation = "Disappearance of aldehyde with simultaneous production of alcohol and carboxylate salt",
                importance = "Classic disproportionation method for aldehydes that cannot form enolates."
            )
        ),
        identificationTests = listOf(
            OrganicTest(
                testName = "Tollens' Silver Mirror Test",
                reagent = "Ammoniacal Silver Nitrate [Ag(NH₃)₂]⁺",
                observation = "Deposit of a shining, reflective Silver Mirror on inner tube wall.",
                explanation = "Aldehydes reduce Ag⁺ to metallic silver Ag⁰ while being oxidized to carboxylates. Ketones do not react.",
                chemicalEquation = "R–CHO + 2[Ag(NH₃)₂]⁺ + 3OH⁻ → R–COO⁻ + 2Ag ↓ (Silver Mirror) + 4NH₃ + 2H₂O"
            ),
            OrganicTest(
                testName = "Fehling's Solution Test",
                reagent = "Fehling A (CuSO₄) + Fehling B (Rochelle salt + NaOH)",
                observation = "Deep blue solution turns into an opaque Red / Brick-red precipitate of Cu₂O.",
                explanation = "Aliphatic aldehydes reduce alkaline Cu²⁺ tartrate to red Cuprous oxide (Cu₂O).",
                chemicalEquation = "R–CHO + 2Cu²⁺ + 5OH⁻ → R–COO⁻ + Cu₂O ↓ (Brick Red ppt) + 3H₂O"
            )
        )
    )

    val ketones = OrganicFunctionalGroup(
        id = "ketones",
        name = "Ketones",
        hindiName = "कीटोन (कार्बोनिल >C=O समूह)",
        category = OrganicCategory.OXYGEN_CONTAINING,
        generalFormula = "R–CO–R'",
        condensedStructure = "–CO–",
        visualStructure2D = "      O\n      ||\n  R – C – R'",
        iupacSuffix = "-one (e.g. propan-2-one, butan-2-one)",
        iupacPrefix = "oxo-",
        priorityOrder = 6,
        hybridization = "Carbonyl carbon is sp² hybridized",
        bondAngle = "~120° (Trigonal Planar)",
        polarity = "Polar (dipole moment ~ 2.7 D)",
        boilingPointTrend = "Comparable to aldehydes of similar molar mass; higher than alkanes and ethers, lower than alcohols.",
        solubility = "Acetone is completely miscible with water; higher homologues become progressively insoluble.",
        acidityBasicity = "Alpha hydrogens are weakly acidic (pKa ~ 19–20) due to enolate stabilization.",
        description = "Organic compounds characterized by a carbonyl group (>C=O) bonded to two carbon-containing groups. Less prone to oxidation than aldehydes.",
        iupacNamingRules = listOf(
            "1. Choose longest chain containing the carbonyl carbon.",
            "2. Number chain from end giving carbonyl carbon the lowest locant.",
            "3. Replace terminal -e with -one (e.g., propane → propan-2-one).",
            "4. For cyclic ketones, carbonyl is designated C-1."
        ),
        examples = listOf(
            OrganicExample("Propan-2-one", "Acetone / Dimethyl Ketone", "C₃H₆O", "CH₃COCH₃", "CH₃–CO–CH₃", "Standard laboratory solvent, nail polish remover, industrial chemical"),
            OrganicExample("Butan-2-one", "Methyl Ethyl Ketone (MEK)", "C₄H₈O", "CH₃COCH₂CH₃", "CH₃–CO–CH₂CH₃", "Industrial coatings, paints, and adhesives solvent"),
            OrganicExample("1-Phenylethan-1-one", "Acetophenone", "C₈H₈O", "C₆H₅COCH₃", "C₆H₅–CO–CH₃", "Fragrance component (orange blossom aroma), resin catalyst")
        ),
        reactions = listOf(
            OrganicReaction(
                id = "clemmensen_reduction",
                name = "Clemmensen Reduction",
                reactionType = OrganicReactionType.NAMED_REACTION,
                equation = "R–CO–R' + 4[H] → [Zn(Hg) / Conc. HCl, Reflux] R–CH₂–R' + H₂O",
                reactants = "Ketone or Aldehyde",
                products = "Alkane (Methylene group –CH₂–)",
                reagentsAndCatalyst = "Zinc Amalgam (Zn-Hg) + Concentrated Hydrochloric Acid",
                conditions = "Prolonged reflux in strong acid",
                mechanismType = "Heterogeneous Surface Electron Transfer Reduction",
                mechanismSteps = listOf(
                    "Coordination of carbonyl oxygen to zinc surface.",
                    "Stepwise two-electron transfers from Zn with carbanionic intermediate formation.",
                    "Direct conversion of C=O to –CH₂– without passing through alcohol."
                ),
                keyObservation = "Disappearance of carbonyl absorption and evolution of alkane hydrocarbons",
                importance = "Prime synthetic strategy to convert Friedel-Crafts acyl aromatic products into alkylbenzenes."
            ),
            OrganicReaction(
                id = "iodoform_reaction_ketone",
                name = "Iodoform Reaction (Haloform Test)",
                reactionType = OrganicReactionType.NAMED_REACTION,
                equation = "CH₃–CO–R + 3I₂ + 4NaOH → CHI₃ ↓ (Iodoform) + R–COONa + 3NaI + 3H₂O",
                reactants = "Methyl ketone (CH₃–CO–R), Iodine, Sodium hydroxide",
                products = "Iodoform (Triiodomethane), Sodium carboxylate",
                reagentsAndCatalyst = "Iodine in aqueous NaOH (NaOI)",
                conditions = "Gentle warming to 60°C",
                mechanismType = "Exhaustive Alpha-Iodination followed by Nucleophilic Acyl Cleavage",
                mechanismSteps = listOf(
                    "Alpha-hydrogens of methyl group are successively replaced by iodine via enolate chemistry.",
                    "Hydroxide attacks carbonyl carbon of triiodomethyl ketone [CI₃–CO–R].",
                    "–CI₃ acts as leaving group and captures proton to form yellow crystalline CHI₃."
                ),
                keyObservation = "Immediate precipitation of yellow crystalline Iodoform with potent medicinal odor",
                importance = "Conclusive diagnostic test for the presence of the acetyl group (CH₃–C=O)."
            )
        ),
        identificationTests = listOf(
            OrganicTest(
                testName = "2,4-DNP (Brady's Reagent Test)",
                reagent = "2,4-Dinitrophenylhydrazine in acidic methanol",
                observation = "Bright Yellow, Orange, or Red crystalline precipitate forms immediately.",
                explanation = "Nucleophilic addition-elimination yields highly conjugated 2,4-dinitrophenylhydrazone.",
                chemicalEquation = "R₂C=O + H₂N–NH–C₆H₃(NO₂)₂ → R₂C=N–NH–C₆H₃(NO₂)₂ ↓ (Orange ppt) + H₂O"
            ),
            OrganicTest(
                testName = "Sodium Nitroprusside Test for Ketones",
                reagent = "Fresh sodium nitroprusside Na₂[Fe(CN)₅NO] + Dilute NaOH",
                observation = "Blood-red or ruby-red color develops.",
                explanation = "Ketones containing α-H form enolates that coordinate with nitroprusside anion.",
                chemicalEquation = "CH₃COCH₃ + OH⁻ → [CH₃COCH₂]⁻ | [CH₃COCH₂]⁻ + [Fe(CN)₅NO]²⁻ → Red Complex"
            )
        )
    )
}
