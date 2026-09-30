package com.example.chemlabx.data

import com.example.chemlabx.data.model.*

object OrganicCarboxylicDerivativesData {

    val carboxylicAcids = OrganicFunctionalGroup(
        id = "carboxylic_acids",
        name = "Carboxylic Acids",
        hindiName = "कार्बोक्सिलिक अम्ल (-COOH समूह)",
        category = OrganicCategory.OXYGEN_CONTAINING,
        generalFormula = "R–COOH",
        condensedStructure = "–COOH",
        visualStructure2D = "      O\n      ||\n  R – C – O – H",
        iupacSuffix = "-oic acid (or -carboxylic acid if cyclic/poly)",
        iupacPrefix = "carboxy-",
        priorityOrder = 1, // Highest priority in standard IUPAC seniority table!
        hybridization = "Carbonyl carbon is sp² hybridized; OH oxygen is sp²-sp³",
        bondAngle = "~120° around carbonyl carbon",
        polarity = "Exceptionally Polar with extensive intermolecular hydrogen bonding forming stable cyclic dimers",
        boilingPointTrend = "Highest boiling points among organic compounds of comparable molecular mass due to stable cyclic hydrogen-bonded dimer formation.",
        solubility = "C1–C4 acids are miscible with water in all proportions; solubility drops above C5.",
        acidityBasicity = "Moderate organic acids (pKa ~ 4–5); significantly more acidic than alcohols and phenols because carboxylate anion (RCOO⁻) is stabilized by two identical electronegative resonance oxygen atoms.",
        description = "Organic compounds characterized by a carboxyl group (-COOH). At the very top of IUPAC functional group hierarchy. Precursors to fats, polymers, and pharmaceuticals.",
        iupacNamingRules = listOf(
            "1. Carboxylic acid group carbon is always numbered as C-1 in the principal chain.",
            "2. Replace terminal -e with suffix -oic acid (e.g., ethane → ethanoic acid).",
            "3. If attached directly to a ring, use suffix -carboxylic acid (e.g., benzenecarboxylic acid).",
            "4. As principal group (Priority #1), it takes precedence over all other functional groups (ketones, alcohols, amines become prefixes)."
        ),
        examples = listOf(
            OrganicExample("Methanoic acid", "Formic Acid", "CH₂O₂", "HCOOH", "H–COOH", "Found in ant and bee stings; industrial textile dyeing and leather tanning agent"),
            OrganicExample("Ethanoic acid", "Acetic Acid (Vinegar)", "C₂H₄O₂", "CH₃COOH", "CH₃–COOH", "4–8% solution is household vinegar; huge industrial vinyl acetate monomer precursor"),
            OrganicExample("Benzoic acid", "Dracylic Acid", "C₇H₆O₂", "C₆H₅COOH", "C₆H₅–COOH", "Aromatic acid used as food preservative (Sodium benzoate E211) and antifungal")
        ),
        reactions = listOf(
            OrganicReaction(
                id = "fischer_esterification",
                name = "Fischer Esterification",
                reactionType = OrganicReactionType.NAMED_REACTION,
                equation = "R–COOH + R'–OH ⇌ [Conc. H₂SO₄, Δ] R–COO–R' + H₂O",
                reactants = "Carboxylic acid, Alcohol",
                products = "Ester, Water",
                reagentsAndCatalyst = "Concentrated H₂SO₄ or dry HCl gas catalyst",
                conditions = "Reflux, removal of water shifts equilibrium forward",
                mechanismType = "Nucleophilic Acyl Substitution (SNAc)",
                mechanismSteps = listOf(
                    "Protonation of carbonyl oxygen enhances electrophilicity of carbonyl carbon.",
                    "Nucleophilic attack of alcohol oxygen gives tetrahedral intermediate.",
                    "Proton transfer transforms –OH into superior leaving group –OH₂⁺.",
                    "Expulsion of water and deprotonation yields pleasant fruity-smelling ester."
                ),
                keyObservation = "Disappearance of pungent vinegar odor and release of distinctive fruity aroma",
                importance = "Fundamental organic reaction for manufacturing fragrances, flavorings, and polyesters (Dacron/PET)."
            ),
            OrganicReaction(
                id = "hvz_reaction",
                name = "Hell-Volhard-Zelinsky (HVZ) Reaction",
                reactionType = OrganicReactionType.NAMED_REACTION,
                equation = "R–CH₂–COOH + X₂ → [Red P / H₂O] R–CH(X)–COOH + HX",
                reactants = "Aliphatic carboxylic acid bearing α-hydrogen, Bromine or Chlorine",
                products = "α-Halo carboxylic acid",
                reagentsAndCatalyst = "Bromine (Br₂) or Chlorine (Cl₂) in presence of catalytic Red Phosphorus",
                conditions = "Gentle heating, followed by aqueous workup",
                mechanismType = "In situ Acyl Halide Formation & Enol Halogenation",
                mechanismSteps = listOf(
                    "Red P reacts with X₂ to form PX₃, converting small amount of acid to acyl halide (R–CH₂–COX).",
                    "Acyl halide undergoes rapid keto-enol tautomerism to enol form.",
                    "Enol attacks Br₂ installing halogen exclusively at α-position.",
                    "Exchange with unreacted carboxylic acid regenerates acyl halide catalyst and produces α-halo acid."
                ),
                keyObservation = "Smooth selective monobromination at the alpha-carbon position",
                importance = "Premier route for the synthesis of α-amino acids (glycine, alanine) via subsequent ammonolysis."
            )
        ),
        identificationTests = listOf(
            OrganicTest(
                testName = "Sodium Bicarbonate Effervescence Test",
                reagent = "5% Aqueous Sodium Bicarbonate (NaHCO₃)",
                observation = "Instant, vigorous effervescence of colorless, odorless Carbon Dioxide (CO₂) gas which turns lime water milky.",
                explanation = "Carboxylic acids are stronger acids than carbonic acid (H₂CO₃) and displace CO₂.",
                chemicalEquation = "R–COOH + NaHCO₃ → R–COONa + H₂O + CO₂ ↑ (Brisk effervescence)"
            ),
            OrganicTest(
                testName = "Ester Test (Fruity Odor Test)",
                reagent = "Ethanol + 2-3 drops Conc. H₂SO₄, heated on water bath",
                observation = "Distinct, pleasant, sweet fruity aroma (e.g. apple, banana, or pineapple odor).",
                explanation = "Acid-catalyzed esterification produces volatile volatile ester esters.",
                chemicalEquation = "R–COOH + C₂H₅OH → [H₂SO₄] R–COOC₂H₅ (Fruity aroma) + H₂O"
            )
        )
    )

    val esters = OrganicFunctionalGroup(
        id = "esters",
        name = "Esters",
        hindiName = "एस्टर (फल जैसी मीठी सुगंध)",
        category = OrganicCategory.OXYGEN_CONTAINING,
        generalFormula = "R–COO–R'",
        condensedStructure = "–COO–",
        visualStructure2D = "      O\n      ||\n  R – C – O – R'",
        iupacSuffix = "-oate (e.g. ethyl ethanoate)",
        iupacPrefix = "alkoxycarbonyl- or alkanoyloxy-",
        priorityOrder = 3,
        hybridization = "Carbonyl carbon is sp²; ether oxygen is sp²-sp³",
        bondAngle = "~120° at carbonyl carbon",
        polarity = "Polar; acts as hydrogen bond acceptor but lacks H-bond donors",
        boilingPointTrend = "Significantly lower than isomeric carboxylic acids (ethyl acetate BP = 77°C vs butanoic acid BP = 163°C) due to absence of O–H hydrogen bonding.",
        solubility = "Low molar mass esters are moderately soluble in water; higher esters are insoluble.",
        acidityBasicity = "Alpha hydrogens are weakly acidic (pKa ~ 25), enabling Claisen condensation.",
        description = "Carboxylic acid derivatives formed by replacing the -OH with an -OR' alkoxy group. Noted for pleasant fruity odors; constituents of natural fats and oils (triglycerides).",
        iupacNamingRules = listOf(
            "1. The alkyl group (R') bonded to the oxygen atom is named FIRST as a separate word.",
            "2. The acyl portion (R–COO–) is named second, replacing suffix -ic acid with -oate.",
            "3. Example: CH₃–COO–C₂H₅ is Ethyl ethanoate (Ethyl acetate)."
        ),
        examples = listOf(
            OrganicExample("Ethyl ethanoate", "Ethyl Acetate", "C₄H₈O₂", "CH₃COOCH₂CH₃", "CH₃–COO–CH₂CH₃", "Fruity pear-like odor; solvent for nail polish and decaffeinating coffee"),
            OrganicExample("Methyl salicylate", "Oil of Wintergreen", "C₈H₈O₃", "C₆H₄(OH)COOCH₃", "o-HO–C₆H₄–COOCH₃", "Pain-relieving topical analgesic ointment active compound"),
            OrganicExample("Isoamyl acetate", "Banana Oil", "C₇H₁₄O₂", "CH₃COO(CH₂)₂CH(CH₃)₂", "CH₃–COO–CH₂CH₂CH(CH₃)₂", "Potent natural banana aroma and bee alarm pheromone")
        ),
        reactions = listOf(
            OrganicReaction(
                id = "saponification",
                name = "Saponification (Alkaline Hydrolysis of Esters)",
                reactionType = OrganicReactionType.NAMED_REACTION,
                equation = "R–COO–R' + NaOH → [Δ] R–COONa (Soap) + R'–OH",
                reactants = "Ester, Sodium hydroxide",
                products = "Sodium carboxylate (Soap salt), Alcohol",
                reagentsAndCatalyst = "Aqueous or ethanolic NaOH / KOH",
                conditions = "Refluxing temperature",
                mechanismType = "Bimolecular Base-Catalyzed Acyl Substitution (BAC2)",
                mechanismSteps = listOf(
                    "Nucleophilic hydroxide ion attacks carbonyl carbon giving tetrahedral alkoxide.",
                    "Alkoxide group (–OR'⁻) is expelled as leaving group forming carboxylic acid.",
                    "Irreversible, rapid proton transfer from carboxylic acid to alkoxide yields carboxylate salt and alcohol."
                ),
                keyObservation = "Clear homogenous solution produces creamy lathering soap precipitate",
                importance = "Ancient and modern chemical basis for manufacturing commercial soap and cleansing detergents."
            )
        ),
        identificationTests = listOf(
            OrganicTest(
                testName = "Hydroxamic Acid Test for Esters",
                reagent = "Hydroxylamine hydrochloride (NH₂OH·HCl) + KOH, then neutral FeCl₃",
                observation = "Development of an intense Magenta / Deep Burgundy-Violet color.",
                explanation = "Ester reacts with hydroxylamine to form hydroxamic acid, which forms a vivid purple chelate complex with Fe³⁺.",
                chemicalEquation = "RCOOR' + NH₂OH → RCONHOH | RCONHOH + Fe³⁺ → Deep Violet Chelate"
            )
        )
    )

    val acylHalides = OrganicFunctionalGroup(
        id = "acyl_halides",
        name = "Acyl Halides (Acid Chlorides)",
        hindiName = "ऐसिल हैलाइड (-COCl समूह)",
        category = OrganicCategory.OXYGEN_CONTAINING,
        generalFormula = "R–CO–X (where X = Cl, Br)",
        condensedStructure = "–COCl",
        visualStructure2D = "      O\n      ||\n  R – C – Cl",
        iupacSuffix = "-oyl halide (e.g. ethanoyl chloride)",
        iupacPrefix = "halocarbonyl- (e.g. chlorocarbonyl-)",
        priorityOrder = 4,
        hybridization = "Carbonyl carbon is sp² hybridized",
        bondAngle = "~120°",
        polarity = "Strongly Polar, highly electrophilic carbonyl center",
        boilingPointTrend = "Boil lower than corresponding carboxylic acids due to lack of hydrogen bonding.",
        solubility = "Reacts violently with water (hydrolyzes rapidly into RCOOH and HCl); soluble in dry inert solvents.",
        acidityBasicity = "Neutral but extremely susceptible to nucleophiles due to excellent chloride leaving group.",
        description = "Most reactive derivatives of carboxylic acids. Powerful acylating agents in organic synthesis.",
        iupacNamingRules = listOf(
            "1. Name the acyl group from the parent acid (replace -ic acid with -oyl).",
            "2. Add the name of the halide as a separate word (e.g., ethanoyl chloride).",
            "3. If attached to a ring, use -carbonyl chloride (e.g., benzenecarbonyl chloride)."
        ),
        examples = listOf(
            OrganicExample("Ethanoyl chloride", "Acetyl Chloride", "C₂H₃ClO", "CH₃COCl", "CH₃–CO–Cl", "Highly fuming acylation reagent used in organic synthesis"),
            OrganicExample("Benzoyl chloride", "Benzenecarbonyl Chloride", "C₇H₅ClO", "C₆H₅COCl", "C₆H₅–CO–Cl", "Used in Schotten-Baumann reaction to benzoylate alcohols and amines")
        ),
        reactions = listOf(
            OrganicReaction(
                id = "schotten_baumann",
                name = "Schotten-Baumann Reaction",
                reactionType = OrganicReactionType.NAMED_REACTION,
                equation = "C₆H₅COCl + C₆H₅NH₂ → [Aqueous NaOH] C₆H₅CONH–C₆H₅ (Benzanilide) + NaCl + H₂O",
                reactants = "Acyl chloride, Amine or Phenol",
                products = "Amide or Ester",
                reagentsAndCatalyst = "Aqueous NaOH to neutralize liberated HCl",
                conditions = "Biphasic room temperature stirring",
                mechanismType = "Nucleophilic Acyl Substitution",
                mechanismSteps = listOf(
                    "Amine attacks carbonyl carbon of acyl chloride.",
                    "Chloride leaving group departs rapidly.",
                    "Aqueous NaOH consumes HCl shifting reaction to completion."
                ),
                keyObservation = "Immediate precipitation of insoluble crystalline amide",
                importance = "Standard industrial method for peptide synthesis and drug derivation."
            )
        ),
        identificationTests = listOf(
            OrganicTest(
                testName = "Silver Nitrate Test (Instant AgCl)",
                reagent = "Aqueous AgNO₃ solution",
                observation = "Immediate dense, curdy white precipitate of AgCl soluble in ammonium hydroxide.",
                explanation = "Rapid hydrolysis releases free chloride ions which react with silver ions.",
                chemicalEquation = "RCOCl + H₂O → RCOOH + HCl | HCl + AgNO₃ → AgCl ↓ (Curdy white ppt) + HNO₃"
            )
        )
    )

    val acidAnhydrides = OrganicFunctionalGroup(
        id = "acid_anhydrides",
        name = "Acid Anhydrides",
        hindiName = "अम्ल ऐनहाइड्राइड (-CO-O-CO-)",
        category = OrganicCategory.OXYGEN_CONTAINING,
        generalFormula = "R–CO–O–CO–R'",
        condensedStructure = "(RCO)₂O",
        visualStructure2D = "      O       O\n      ||      ||\n  R – C – O – C – R'",
        iupacSuffix = "-oic anhydride (e.g. ethanoic anhydride)",
        iupacPrefix = "anhydride",
        priorityOrder = 2,
        hybridization = "Carbonyl carbons are sp²; central bridging oxygen is sp³",
        bondAngle = "~120° around carbonyls, ~110° around oxygen bridge",
        polarity = "Polar",
        boilingPointTrend = "Higher than esters and acyl chlorides; lower than parent carboxylic acids.",
        solubility = "Hydrolyzes steadily in water to regenerate parent carboxylic acids.",
        acidityBasicity = "Neutral, active electrophile.",
        description = "Compounds formed formally by the condensation of two carboxylic acid molecules with elimination of water. Second only to acyl halides in acylation reactivity.",
        iupacNamingRules = listOf(
            "1. Symmetrical anhydrides replace the word 'acid' with 'anhydride' (e.g., ethanoic anhydride).",
            "2. Mixed anhydrides list parent acids alphabetically (e.g., ethanoic propanoic anhydride)."
        ),
        examples = listOf(
            OrganicExample("Ethanoic anhydride", "Acetic Anhydride", "C₄H₆O₃", "(CH₃CO)₂O", "CH₃CO–O–COCH₃", "Industrial reagent used to manufacture cellulose acetate and Aspirin"),
            OrganicExample("Phthalic anhydride", "1,3-Isobenzofurandione", "C₈H₄O₃", "C₆H₄(CO)₂O", "Cyclic anhydride", "Key raw material for alkyd resins and phenolphthalein dye")
        ),
        reactions = listOf(
            OrganicReaction(
                id = "aspirin_synthesis",
                name = "Synthesis of Aspirin (Acetylation)",
                reactionType = OrganicReactionType.NAMED_REACTION,
                equation = "o-HO–C₆H₄–COOH + (CH₃CO)₂O → [Conc. H₂SO₄, Δ] o-CH₃COO–C₆H₄–COOH (Aspirin) + CH₃COOH",
                reactants = "Salicylic acid, Acetic anhydride",
                products = "Acetylsalicylic acid (Aspirin), Acetic acid",
                reagentsAndCatalyst = "Catalytic Concentrated H₂SO₄ or Phosphoric acid",
                conditions = "Warming at 50–60°C on water bath",
                mechanismType = "Nucleophilic Acyl Substitution",
                mechanismSteps = listOf(
                    "Phenolic –OH attacks activated carbonyl of acetic anhydride.",
                    "Acetate ion acts as leaving group.",
                    "Precipitation of aspirin crystals upon adding cold water."
                ),
                keyObservation = "Precipitation of lustrous white needle-shaped crystals of Aspirin",
                importance = "The most widely taken analgesic and cardioprotective medication on earth."
            )
        ),
        identificationTests = listOf(
            OrganicTest(
                testName = "Fluorescein Dye Test (for Phthalic Anhydride)",
                reagent = "Resorcinol + Concentrated H₂SO₄, heated then poured into dilute NaOH",
                observation = "Brilliant, intense fluorescent green solution.",
                explanation = "Condensation produces fluorescein dye with spectacular optical fluorescence.",
                chemicalEquation = "Phthalic anhydride + 2 Resorcinol → Fluorescein (Intense green fluorescence)"
            )
        )
    )
}
