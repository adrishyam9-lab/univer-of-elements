package com.example.chemlabx.data

import com.example.chemlabx.data.model.Atom3D
import com.example.chemlabx.data.model.BondType
import com.example.chemlabx.data.model.ChemicalBond
import com.example.chemlabx.data.model.Compound

object CompoundsExtendedDataPart2 {
    val compounds: List<Compound> = listOf(
        Compound(
            id = "methanol",
            name = "Methanol",
            iupacName = "Methanol",
            formula = "CH₃OH",
            category = "Organic Alcohol",
            molarMass = 32.04,
            geometry = "Tetrahedral Carbon, Bent Oxygen",
            bondAngles = "109.5° (H-C-H), 108.5° (C-O-H)",
            polarity = "Polar (Dipole Moment: 1.70 D)",
            functionalGroups = listOf("Hydroxyl (-OH)"),
            atoms = listOf(
                Atom3D("C", -0.6f, 0f, 0f, 0xFF475569, 1.0f),
                Atom3D("O", 0.7f, 0f, 0f, 0xFFEF4444, 1.1f),
                Atom3D("H", 1.2f, 0.8f, 0f, 0xFFF8FAFC, 0.7f),
                Atom3D("H", -1.0f, 1.0f, 0f, 0xFFF8FAFC, 0.7f),
                Atom3D("H", -1.0f, -0.5f, 0.9f, 0xFFF8FAFC, 0.7f),
                Atom3D("H", -1.0f, -0.5f, -0.9f, 0xFFF8FAFC, 0.7f)
            ),
            bonds = listOf(
                ChemicalBond(0, 1, 1, BondType.COVALENT_POLAR, 142),
                ChemicalBond(1, 2, 1, BondType.COVALENT_POLAR, 96),
                ChemicalBond(0, 3, 1, BondType.COVALENT_NONPOLAR, 109),
                ChemicalBond(0, 4, 1, BondType.COVALENT_NONPOLAR, 109),
                ChemicalBond(0, 5, 1, BondType.COVALENT_NONPOLAR, 109)
            ),
            lewisNotes = "Simplest alcohol consisting of a methyl group linked to a hydroxyl group.",
            description = "Wood alcohol. Industrial solvent, antifreeze, and precursor to formaldehyde and acetic acid.",
            safetyInfo = "TOXIC: Ingestion of as little as 10 mL causes permanent blindness via formic acid optic nerve destruction; 30 mL is lethal.",
            bondExplanation = "Strong polar O-H bond enables extensive intermolecular hydrogen bonding.",
            pubchemCid = 887,
            casNumber = "67-56-1",
            isDangerousSimulationOnly = true
        ),
        Compound(
            id = "acetone",
            name = "Acetone",
            iupacName = "Propan-2-one",
            formula = "CH₃COCH₃",
            category = "Organic Ketone",
            molarMass = 58.08,
            geometry = "Trigonal Planar Carbonyl Carbon",
            bondAngles = "120.0° (C=O)",
            polarity = "Polar (Dipole Moment: 2.88 D)",
            functionalGroups = listOf("Ketone (>C=O)"),
            atoms = listOf(
                Atom3D("C", 0f, 0f, 0f, 0xFF475569, 1.0f),
                Atom3D("O", 0f, 1.22f, 0f, 0xFFEF4444, 1.1f),
                Atom3D("C", -1.25f, -0.7f, 0f, 0xFF475569, 1.0f),
                Atom3D("C", 1.25f, -0.7f, 0f, 0xFF475569, 1.0f)
            ),
            bonds = listOf(
                ChemicalBond(0, 1, 2, BondType.COVALENT_POLAR, 122),
                ChemicalBond(0, 2, 1, BondType.COVALENT_NONPOLAR, 151),
                ChemicalBond(0, 3, 1, BondType.COVALENT_NONPOLAR, 151)
            ),
            lewisNotes = "Carbonyl carbon is sp² hybridized with a C=O double bond flanked by two methyl groups.",
            description = "Universal laboratory cleaning solvent and common household nail polish remover.",
            safetyInfo = "Highly volatile and flammable liquid and vapor.",
            bondExplanation = "Polar carbonyl group makes acetone completely miscible with both water and nonpolar organic solvents.",
            pubchemCid = 180,
            casNumber = "67-64-1"
        ),
        Compound(
            id = "formaldehyde",
            name = "Formaldehyde",
            iupacName = "Methanal",
            formula = "HCHO",
            category = "Organic Aldehyde",
            molarMass = 30.03,
            geometry = "Trigonal Planar",
            bondAngles = "121.7° (H-C=O), 116.5° (H-C-H)",
            polarity = "Polar (Dipole Moment: 2.33 D)",
            functionalGroups = listOf("Aldehyde (-CHO)"),
            atoms = listOf(
                Atom3D("C", 0f, 0f, 0f, 0xFF475569, 1.0f),
                Atom3D("O", 0f, 1.21f, 0f, 0xFFEF4444, 1.1f),
                Atom3D("H", -0.94f, -0.58f, 0f, 0xFFF8FAFC, 0.7f),
                Atom3D("H", 0.94f, -0.58f, 0f, 0xFFF8FAFC, 0.7f)
            ),
            bonds = listOf(
                ChemicalBond(0, 1, 2, BondType.COVALENT_POLAR, 121),
                ChemicalBond(0, 2, 1, BondType.COVALENT_NONPOLAR, 111),
                ChemicalBond(0, 3, 1, BondType.COVALENT_NONPOLAR, 111)
            ),
            lewisNotes = "Simplest aldehyde with sp² hybridized carbonyl carbon bonded to two hydrogens.",
            description = "Pungent gas; 37% aqueous solution is known as formalin, used for biological tissue preservation and Bakelite resins.",
            safetyInfo = "Known human carcinogen and potent sensitizer.",
            bondExplanation = "Strong C=O dipole makes the carbonyl carbon exceptionally electrophilic and reactive.",
            pubchemCid = 712,
            casNumber = "50-00-0"
        ),
        Compound(
            id = "acetaldehyde",
            name = "Acetaldehyde",
            iupacName = "Ethanal",
            formula = "CH₃CHO",
            category = "Organic Aldehyde",
            molarMass = 44.05,
            geometry = "Trigonal Planar Carbonyl",
            bondAngles = "120.0°",
            polarity = "Polar (Dipole Moment: 2.7 D)",
            functionalGroups = listOf("Aldehyde (-CHO)"),
            atoms = listOf(
                Atom3D("C", 0.6f, 0f, 0f, 0xFF475569, 1.0f),
                Atom3D("O", 1.2f, 1.0f, 0f, 0xFFEF4444, 1.1f),
                Atom3D("H", 1.1f, -0.9f, 0f, 0xFFF8FAFC, 0.7f),
                Atom3D("C", -0.9f, 0f, 0f, 0xFF475569, 1.0f)
            ),
            bonds = listOf(
                ChemicalBond(0, 1, 2, BondType.COVALENT_POLAR, 121),
                ChemicalBond(0, 2, 1, BondType.COVALENT_NONPOLAR, 110),
                ChemicalBond(0, 3, 1, BondType.COVALENT_NONPOLAR, 150)
            ),
            lewisNotes = "Carbonyl carbon bonded to hydrogen and methyl group; gives positive Tollens' and Fehling's tests.",
            description = "Metabolite of ethanol in the human liver responsible for alcohol hangovers; precursor to acetic acid.",
            safetyInfo = "Volatile flammable liquid; irritant and suspected carcinogen.",
            bondExplanation = "Alpha-hydrogens on methyl group are acidic (pKa ~ 17) due to enolate resonance stabilization.",
            pubchemCid = 177,
            casNumber = "75-07-0"
        ),
        Compound(
            id = "ethene",
            name = "Ethylene",
            iupacName = "Ethene",
            formula = "C₂H₄",
            category = "Organic Alkene",
            molarMass = 28.05,
            geometry = "Planar",
            bondAngles = "121.3° (H-C=C), 117.4° (H-C-H)",
            polarity = "Non-polar",
            functionalGroups = listOf("Alkene (C=C double bond)"),
            atoms = listOf(
                Atom3D("C", -0.67f, 0f, 0f, 0xFF475569, 1.0f),
                Atom3D("C", 0.67f, 0f, 0f, 0xFF475569, 1.0f),
                Atom3D("H", -1.23f, 0.92f, 0f, 0xFFF8FAFC, 0.7f),
                Atom3D("H", -1.23f, -0.92f, 0f, 0xFFF8FAFC, 0.7f),
                Atom3D("H", 1.23f, 0.92f, 0f, 0xFFF8FAFC, 0.7f),
                Atom3D("H", 1.23f, -0.92f, 0f, 0xFFF8FAFC, 0.7f)
            ),
            bonds = listOf(
                ChemicalBond(0, 1, 2, BondType.COVALENT_NONPOLAR, 134),
                ChemicalBond(0, 2, 1, BondType.COVALENT_NONPOLAR, 108),
                ChemicalBond(0, 3, 1, BondType.COVALENT_NONPOLAR, 108),
                ChemicalBond(1, 4, 1, BondType.COVALENT_NONPOLAR, 108),
                ChemicalBond(1, 5, 1, BondType.COVALENT_NONPOLAR, 108)
            ),
            lewisNotes = "Both carbons are sp² hybridized, sharing one σ bond and one π bond in a rigid planar geometry.",
            description = "Natural plant hormone triggering fruit ripening; world's most produced organic chemical monomer (for polyethylene plastic).",
            safetyInfo = "Extremely flammable gas; asphyxiant in high concentrations.",
            bondExplanation = "The exposed π bond easily undergoes electrophilic additions with halogens and acids.",
            pubchemCid = 6325,
            casNumber = "74-85-1"
        ),
        Compound(
            id = "ethyne",
            name = "Acetylene",
            iupacName = "Ethyne",
            formula = "C₂H₂",
            category = "Organic Alkyne",
            molarMass = 26.04,
            geometry = "Linear",
            bondAngles = "180.0°",
            polarity = "Non-polar",
            functionalGroups = listOf("Alkyne (C≡C triple bond)"),
            atoms = listOf(
                Atom3D("C", -0.6f, 0f, 0f, 0xFF475569, 1.0f),
                Atom3D("C", 0.6f, 0f, 0f, 0xFF475569, 1.0f),
                Atom3D("H", -1.66f, 0f, 0f, 0xFFF8FAFC, 0.7f),
                Atom3D("H", 1.66f, 0f, 0f, 0xFFF8FAFC, 0.7f)
            ),
            bonds = listOf(
                ChemicalBond(0, 1, 3, BondType.COVALENT_NONPOLAR, 120),
                ChemicalBond(0, 2, 1, BondType.COVALENT_NONPOLAR, 106),
                ChemicalBond(1, 3, 1, BondType.COVALENT_NONPOLAR, 106)
            ),
            lewisNotes = "Both carbons are sp hybridized with one σ bond and two orthogonal π bonds.",
            description = "Fuel gas burned in oxy-acetylene welding torches producing flame temperatures over 3300°C.",
            safetyInfo = "Highly unstable and explosive gas under pressure above 2 bar; stored dissolved in acetone.",
            bondExplanation = "High 50% s-character of sp carbon makes the terminal C-H bonds unusually acidic (pKa ~ 25).",
            pubchemCid = 6326,
            casNumber = "74-86-2"
        ),
        Compound(
            id = "propane",
            name = "Propane",
            iupacName = "Propane",
            formula = "C₃H₈",
            category = "Organic Hydrocarbon (Alkane)",
            molarMass = 44.1,
            geometry = "Tetrahedral Carbon Chain",
            bondAngles = "109.5°",
            polarity = "Non-polar",
            functionalGroups = listOf("Alkane"),
            atoms = listOf(
                Atom3D("C", -1.27f, 0f, 0f, 0xFF475569, 1.0f),
                Atom3D("C", 0f, 0.6f, 0f, 0xFF475569, 1.0f),
                Atom3D("C", 1.27f, 0f, 0f, 0xFF475569, 1.0f)
            ),
            bonds = listOf(
                ChemicalBond(0, 1, 1, BondType.COVALENT_NONPOLAR, 154),
                ChemicalBond(1, 2, 1, BondType.COVALENT_NONPOLAR, 154)
            ),
            lewisNotes = "All three carbons are sp³ hybridized forming a zig-zag saturated chain.",
            description = "Liquefied petroleum gas (LPG) stored under pressure in cylinders for residential heating and barbecue cooking.",
            safetyInfo = "Extremely flammable liquefied gas; vapors are heavier than air.",
            bondExplanation = "Held together purely by London dispersion forces, leading to a low boiling point (-42°C).",
            pubchemCid = 6334,
            casNumber = "74-98-6"
        ),
        Compound(
            id = "butane",
            name = "Butane",
            iupacName = "Butane",
            formula = "C₄H₁₀",
            category = "Organic Hydrocarbon (Alkane)",
            molarMass = 58.12,
            geometry = "Tetrahedral Zig-Zag Chain",
            bondAngles = "109.5°",
            polarity = "Non-polar",
            functionalGroups = listOf("Alkane"),
            atoms = listOf(
                Atom3D("C", -1.9f, 0f, 0f, 0xFF475569, 1.0f),
                Atom3D("C", -0.6f, 0.6f, 0f, 0xFF475569, 1.0f),
                Atom3D("C", 0.6f, -0.6f, 0f, 0xFF475569, 1.0f),
                Atom3D("C", 1.9f, 0f, 0f, 0xFF475569, 1.0f)
            ),
            bonds = listOf(
                ChemicalBond(0, 1, 1, BondType.COVALENT_NONPOLAR, 154),
                ChemicalBond(1, 2, 1, BondType.COVALENT_NONPOLAR, 154),
                ChemicalBond(2, 3, 1, BondType.COVALENT_NONPOLAR, 154)
            ),
            lewisNotes = "Unbranched four-carbon alkane with anti-periplanar stable conformation.",
            description = "Common fuel inside pocket cigarette lighters and portable camping gas stoves.",
            safetyInfo = "Extremely flammable gas under pressure.",
            bondExplanation = "Non-polar covalent bonding with slight conformational flexibility around C2-C3 bond.",
            pubchemCid = 7843,
            casNumber = "106-97-8"
        ),
        Compound(
            id = "diethyl_ether",
            name = "Diethyl Ether",
            iupacName = "Ethoxyethane",
            formula = "C₄H₁₀O",
            category = "Organic Ether",
            molarMass = 74.12,
            geometry = "Bent Oxygen",
            bondAngles = "111.7° (C-O-C)",
            polarity = "Weakly Polar (Dipole Moment: 1.15 D)",
            functionalGroups = listOf("Ether (C-O-C)"),
            atoms = listOf(
                Atom3D("O", 0f, 0.4f, 0f, 0xFFEF4444, 1.1f),
                Atom3D("C", -1.2f, -0.3f, 0f, 0xFF475569, 1.0f),
                Atom3D("C", 1.2f, -0.3f, 0f, 0xFF475569, 1.0f),
                Atom3D("C", -2.4f, 0.5f, 0f, 0xFF475569, 1.0f),
                Atom3D("C", 2.4f, 0.5f, 0f, 0xFF475569, 1.0f)
            ),
            bonds = listOf(
                ChemicalBond(0, 1, 1, BondType.COVALENT_POLAR, 143),
                ChemicalBond(0, 2, 1, BondType.COVALENT_POLAR, 143),
                ChemicalBond(1, 3, 1, BondType.COVALENT_NONPOLAR, 154),
                ChemicalBond(2, 4, 1, BondType.COVALENT_NONPOLAR, 154)
            ),
            lewisNotes = "Oxygen has two lone pairs and two single bonds to ethyl groups. Steric bulk opens C-O-C angle to 111.7°.",
            description = "Historic general inhalation surgical anesthetic introduced in 1846; laboratory extraction solvent.",
            safetyInfo = "Extremely flammable volatile solvent (BP 34.6°C); forms explosive peroxides on air exposure.",
            bondExplanation = "Lacks O-H bonds, preventing hydrogen bonding with itself, resulting in a very low boiling point.",
            pubchemCid = 3283,
            casNumber = "60-29-7"
        ),
        Compound(
            id = "chloroform",
            name = "Chloroform",
            iupacName = "Trichloromethane",
            formula = "CHCl₃",
            category = "Organohalogen / Solvent",
            molarMass = 119.38,
            geometry = "Tetrahedral",
            bondAngles = "109.5°",
            polarity = "Polar (Dipole Moment: 1.04 D)",
            functionalGroups = listOf("Alkyl Halide"),
            atoms = listOf(
                Atom3D("C", 0f, 0f, 0f, 0xFF475569, 1.0f),
                Atom3D("H", 0f, 1.1f, 0f, 0xFFF8FAFC, 0.7f),
                Atom3D("Cl", 1.4f, -0.4f, 0f, 0xFF10B981, 1.2f),
                Atom3D("Cl", -0.7f, -0.4f, 1.2f, 0xFF10B981, 1.2f),
                Atom3D("Cl", -0.7f, -0.4f, -1.2f, 0xFF10B981, 1.2f)
            ),
            bonds = listOf(
                ChemicalBond(0, 1, 1, BondType.COVALENT_POLAR, 109),
                ChemicalBond(0, 2, 1, BondType.COVALENT_POLAR, 176),
                ChemicalBond(0, 3, 1, BondType.COVALENT_POLAR, 176),
                ChemicalBond(0, 4, 1, BondType.COVALENT_POLAR, 176)
            ),
            lewisNotes = "Central carbon with one C-H bond and three C-Cl polar bonds. Dipoles sum to give net molecular polarity.",
            description = "Historic Victorian inhalation anesthetic; precursor to Teflon polytetrafluoroethylene (PTFE).",
            safetyInfo = "Toxic upon inhalation; decomposes in light and air to form deadly phosgene gas (COCl₂).",
            bondExplanation = "Three highly electronegative chlorine atoms create a strong electron deficit on the central carbon.",
            pubchemCid = 6328,
            casNumber = "67-66-3"
        ),
        Compound(
            id = "carbon_tetrachloride",
            name = "Carbon Tetrachloride",
            iupacName = "Tetrachloromethane",
            formula = "CCl₄",
            category = "Organohalogen / Non-polar Solvent",
            molarMass = 153.82,
            geometry = "Regular Tetrahedral",
            bondAngles = "109.5°",
            polarity = "Non-polar (Symmetrical dipoles cancel)",
            functionalGroups = listOf("Halocarbon"),
            atoms = listOf(
                Atom3D("C", 0f, 0f, 0f, 0xFF475569, 1.0f),
                Atom3D("Cl", 0f, 1.76f, 0f, 0xFF10B981, 1.2f),
                Atom3D("Cl", 1.66f, -0.58f, 0f, 0xFF10B981, 1.2f),
                Atom3D("Cl", -0.83f, -0.58f, 1.44f, 0xFF10B981, 1.2f),
                Atom3D("Cl", -0.83f, -0.58f, -1.44f, 0xFF10B981, 1.2f)
            ),
            bonds = listOf(
                ChemicalBond(0, 1, 1, BondType.COVALENT_POLAR, 176),
                ChemicalBond(0, 2, 1, BondType.COVALENT_POLAR, 176),
                ChemicalBond(0, 3, 1, BondType.COVALENT_POLAR, 176),
                ChemicalBond(0, 4, 1, BondType.COVALENT_POLAR, 176)
            ),
            lewisNotes = "All four C-Cl polar bond vectors cancel out completely due to perfect tetrahedral symmetry.",
            description = "Heavy non-flammable liquid historically used in fire extinguishers and dry cleaning.",
            safetyInfo = "Extremely hepatotoxic (liver-damaging) and potent ozone-depleting substance (Montreal Protocol).",
            bondExplanation = "Zero net molecular dipole moment despite containing four strongly polarized bonds.",
            pubchemCid = 5943,
            casNumber = "56-23-5"
        ),
        Compound(
            id = "formic_acid",
            name = "Formic Acid",
            iupacName = "Methanoic Acid",
            formula = "HCOOH",
            category = "Organic Carboxylic Acid",
            molarMass = 46.03,
            geometry = "Planar Carboxyl",
            bondAngles = "120.0°",
            polarity = "Polar (Dipole Moment: 1.41 D)",
            functionalGroups = listOf("Carboxylic Acid (-COOH)"),
            atoms = listOf(
                Atom3D("C", 0f, 0f, 0f, 0xFF475569, 1.0f),
                Atom3D("O", 0f, 1.20f, 0f, 0xFFEF4444, 1.1f),
                Atom3D("O", 1.15f, -0.6f, 0f, 0xFFEF4444, 1.1f),
                Atom3D("H", -0.95f, -0.5f, 0f, 0xFFF8FAFC, 0.7f),
                Atom3D("H", 1.9f, -0.1f, 0f, 0xFFF8FAFC, 0.7f)
            ),
            bonds = listOf(
                ChemicalBond(0, 1, 2, BondType.COVALENT_POLAR, 120),
                ChemicalBond(0, 2, 1, BondType.COVALENT_POLAR, 134),
                ChemicalBond(0, 3, 1, BondType.COVALENT_NONPOLAR, 109),
                ChemicalBond(2, 4, 1, BondType.COVALENT_POLAR, 96)
            ),
            lewisNotes = "Simplest carboxylic acid. The hydrogen attached directly to carbonyl carbon gives it reducing properties.",
            description = "Natural defensive venom in ant and bee stings; antibacterial preservative in livestock feed.",
            safetyInfo = "Corrosive liquid causing severe skin blistering and deep burns.",
            bondExplanation = "Stronger acid than acetic acid (pKa = 3.75 vs 4.76) because it lacks an electron-donating methyl group.",
            pubchemCid = 284,
            casNumber = "64-18-6"
        ),
        Compound(
            id = "ethyl_acetate",
            name = "Ethyl Acetate",
            iupacName = "Ethyl Ethanoate",
            formula = "CH₃COOCH₂CH₃",
            category = "Organic Ester",
            molarMass = 88.11,
            geometry = "Planar Ester Core",
            bondAngles = "120.0° (C=O)",
            polarity = "Polar (Dipole Moment: 1.78 D)",
            functionalGroups = listOf("Ester (-COO-)"),
            atoms = listOf(
                Atom3D("C", 0f, 0f, 0f, 0xFF475569, 1.0f),
                Atom3D("O", 0f, 1.21f, 0f, 0xFFEF4444, 1.1f),
                Atom3D("O", 1.15f, -0.6f, 0f, 0xFFEF4444, 1.1f),
                Atom3D("C", -1.25f, -0.7f, 0f, 0xFF475569, 1.0f),
                Atom3D("C", 2.3f, 0.1f, 0f, 0xFF475569, 1.0f)
            ),
            bonds = listOf(
                ChemicalBond(0, 1, 2, BondType.COVALENT_POLAR, 121),
                ChemicalBond(0, 2, 1, BondType.COVALENT_POLAR, 135),
                ChemicalBond(0, 3, 1, BondType.COVALENT_NONPOLAR, 151),
                ChemicalBond(2, 4, 1, BondType.COVALENT_POLAR, 145)
            ),
            lewisNotes = "Ester linkage formed by condensation of acetic acid and ethanol.",
            description = "Sweet fruity pear-like aroma; common nail polish remover and industrial decaffeination solvent.",
            safetyInfo = "Highly flammable liquid and vapor.",
            bondExplanation = "Acts as hydrogen-bond acceptor but lacks donor protons, resulting in high volatility.",
            pubchemCid = 8857,
            casNumber = "141-78-6"
        ),
        Compound(
            id = "benzoic_acid",
            name = "Benzoic Acid",
            iupacName = "Benzoic Acid",
            formula = "C₆H₅COOH",
            category = "Aromatic Carboxylic Acid",
            molarMass = 122.12,
            geometry = "Planar Benzene Ring with Coplanar Carboxyl",
            bondAngles = "120.0°",
            polarity = "Polar (Dipole Moment: 1.72 D)",
            functionalGroups = listOf("Carboxyl (-COOH)", "Phenyl Ring"),
            atoms = listOf(
                Atom3D("C", 0f, 0f, 0f, 0xFF475569, 1.0f),
                Atom3D("C", 1.4f, 0f, 0f, 0xFF475569, 1.0f),
                Atom3D("O", 1.9f, 1.1f, 0f, 0xFFEF4444, 1.1f),
                Atom3D("O", 1.9f, -1.1f, 0f, 0xFFEF4444, 1.1f)
            ),
            bonds = listOf(
                ChemicalBond(0, 1, 1, BondType.COVALENT_NONPOLAR, 148),
                ChemicalBond(1, 2, 2, BondType.COVALENT_POLAR, 121),
                ChemicalBond(1, 3, 1, BondType.COVALENT_POLAR, 135)
            ),
            lewisNotes = "Carboxyl group conjugated directly to the aromatic ring.",
            description = "White crystalline solid; its sodium salt (Sodium Benzoate E211) is a universal food preservative inhibiting fungal growth.",
            safetyInfo = "Causes skin irritation and damage to organs through prolonged exposure.",
            bondExplanation = "Conjugation of carboxyl carbonyl with aromatic π-electrons stabilizes the planar conformation.",
            pubchemCid = 243,
            casNumber = "65-85-0"
        ),
        Compound(
            id = "phenol",
            name = "Phenol",
            iupacName = "Phenol",
            formula = "C₆H₅OH",
            category = "Aromatic Hydroxy Compound",
            molarMass = 94.11,
            geometry = "Planar Ring with sp² Oxygen",
            bondAngles = "120.0°",
            polarity = "Polar (Dipole Moment: 1.22 D)",
            functionalGroups = listOf("Phenolic Hydroxyl (-OH)"),
            atoms = listOf(
                Atom3D("C", 0f, 0f, 0f, 0xFF475569, 1.0f),
                Atom3D("O", 1.35f, 0f, 0f, 0xFFEF4444, 1.1f),
                Atom3D("H", 1.9f, 0.7f, 0f, 0xFFF8FAFC, 0.7f)
            ),
            bonds = listOf(
                ChemicalBond(0, 1, 1, BondType.COVALENT_POLAR, 136),
                ChemicalBond(1, 2, 1, BondType.COVALENT_POLAR, 96)
            ),
            lewisNotes = "Oxygen lone pair delocalizes into aromatic ring via resonance, giving C-O bond partial double bond character.",
            description = "Carbolic acid. Introduced by Joseph Lister as the pioneer antiseptic; precursor to aspirin and Bakelite.",
            safetyInfo = "Corrosive and rapidly absorbed through skin causing chemical burns and systemic toxicity.",
            bondExplanation = "Substantially more acidic than aliphatic alcohols (pKa ~ 10 vs 16) due to resonance stabilization of phenoxide anion.",
            pubchemCid = 996,
            casNumber = "108-95-2"
        ),
        Compound(
            id = "toluene",
            name = "Toluene",
            iupacName = "Methylbenzene",
            formula = "C₇H₈",
            category = "Aromatic Hydrocarbon",
            molarMass = 92.14,
            geometry = "Planar Ring with Tetrahedral Methyl",
            bondAngles = "120.0°",
            polarity = "Weakly Polar (Dipole Moment: 0.36 D)",
            functionalGroups = listOf("Phenyl Ring", "Methyl (-CH₃)"),
            atoms = listOf(
                Atom3D("C", 0f, 0f, 0f, 0xFF475569, 1.0f),
                Atom3D("C", 1.5f, 0f, 0f, 0xFF475569, 1.0f)
            ),
            bonds = listOf(
                ChemicalBond(0, 1, 1, BondType.COVALENT_NONPOLAR, 151)
            ),
            lewisNotes = "Methyl group is electron-donating via hyperconjugation and inductive effect (+I).",
            description = "Common paint thinner and solvent; industrial starting material for synthesizing TNT (trinitrotoluene).",
            safetyInfo = "Flammable liquid; inhalation harms central nervous system.",
            bondExplanation = "Activates the aromatic ring toward electrophilic substitution at ortho and para positions.",
            pubchemCid = 1140,
            casNumber = "108-88-3"
        ),
        Compound(
            id = "aniline",
            name = "Aniline",
            iupacName = "Benzenamine",
            formula = "C₆H₅NH₂",
            category = "Aromatic Primary Amine",
            molarMass = 93.13,
            geometry = "Planar Ring with Pyramidal Amino Group",
            bondAngles = "120.0° (Ring), 113.0° (H-N-H)",
            polarity = "Polar (Dipole Moment: 1.53 D)",
            functionalGroups = listOf("Primary Amine (-NH₂)", "Aromatic Ring"),
            atoms = listOf(
                Atom3D("C", 0f, 0f, 0f, 0xFF475569, 1.0f),
                Atom3D("N", 1.4f, 0f, 0f, 0xFF3B82F6, 1.1f),
                Atom3D("H", 1.9f, 0.7f, 0f, 0xFFF8FAFC, 0.7f),
                Atom3D("H", 1.9f, -0.7f, 0f, 0xFFF8FAFC, 0.7f)
            ),
            bonds = listOf(
                ChemicalBond(0, 1, 1, BondType.COVALENT_POLAR, 140),
                ChemicalBond(1, 2, 1, BondType.COVALENT_POLAR, 101),
                ChemicalBond(1, 3, 1, BondType.COVALENT_POLAR, 101)
            ),
            lewisNotes = "Nitrogen lone pair delocalizes strongly into the aromatic π cloud, making aniline a weak base (pKb = 9.4).",
            description = "Oily liquid darkening to brown; industrial precursor to indigo dye, paracetamol, and polyurethane foam.",
            safetyInfo = "Toxic by inhalation and skin absorption; induces methemoglobinemia.",
            bondExplanation = "Strong resonance interaction (+M effect) strongly activates ring toward electrophilic attack.",
            pubchemCid = 6115,
            casNumber = "62-53-3"
        ),
        Compound(
            id = "urea",
            name = "Urea",
            iupacName = "Carbamide",
            formula = "CH₄N₂O",
            category = "Organic Amide / Diamide",
            molarMass = 60.06,
            geometry = "Planar Carbonyl with Two Amino Groups",
            bondAngles = "120.0°",
            polarity = "Highly Polar (Dipole Moment: 4.56 D)",
            functionalGroups = listOf("Amide (-CONH₂)"),
            atoms = listOf(
                Atom3D("C", 0f, 0f, 0f, 0xFF475569, 1.0f),
                Atom3D("O", 0f, 1.26f, 0f, 0xFFEF4444, 1.1f),
                Atom3D("N", -1.16f, -0.6f, 0f, 0xFF3B82F6, 1.1f),
                Atom3D("N", 1.16f, -0.6f, 0f, 0xFF3B82F6, 1.1f)
            ),
            bonds = listOf(
                ChemicalBond(0, 1, 2, BondType.COVALENT_POLAR, 126),
                ChemicalBond(0, 2, 1, BondType.COVALENT_POLAR, 135),
                ChemicalBond(0, 3, 1, BondType.COVALENT_POLAR, 135)
            ),
            lewisNotes = "Two amino groups attached to a carbonyl carbon; resonance delocalizes both nitrogen lone pairs into oxygen.",
            description = "Synthesized by Friedrich Wöhler in 1828 from ammonium cyanate, famously disproving vitalism. Key nitrogen fertilizer.",
            safetyInfo = "Non-hazardous biological metabolic waste product of protein digestion.",
            bondExplanation = "Extensive hydrogen-bonding network makes urea an exceptional protein-denaturing agent and water-soluble solid.",
            pubchemCid = 1176,
            casNumber = "57-13-6"
        ),
        Compound(
            id = "glucose",
            name = "D-Glucose",
            iupacName = "(2R,3S,4R,5R)-2,3,4,5,6-Pentahydroxyhexanal",
            formula = "C₆H₁₂O₆",
            category = "Biomolecule / Monosaccharide",
            molarMass = 180.16,
            geometry = "Pyranose Ring (Chair Conformation)",
            bondAngles = "109.5°",
            polarity = "Extremely Polar (Hydrophilic)",
            functionalGroups = listOf("Aldohexose", "Polyhydroxy (5 -OH groups)"),
            atoms = listOf(
                Atom3D("C", 0f, 0f, 0f, 0xFF475569, 1.0f),
                Atom3D("C", 1.4f, 0f, 0f, 0xFF475569, 1.0f),
                Atom3D("O", 2.0f, 1.1f, 0f, 0xFFEF4444, 1.1f),
                Atom3D("C", 1.4f, 2.2f, 0f, 0xFF475569, 1.0f),
                Atom3D("C", 0f, 2.2f, 0f, 0xFF475569, 1.0f),
                Atom3D("C", -0.7f, 1.1f, 0f, 0xFF475569, 1.0f)
            ),
            bonds = listOf(
                ChemicalBond(0, 1, 1, BondType.COVALENT_NONPOLAR, 153),
                ChemicalBond(1, 2, 1, BondType.COVALENT_POLAR, 143),
                ChemicalBond(2, 3, 1, BondType.COVALENT_POLAR, 143),
                ChemicalBond(3, 4, 1, BondType.COVALENT_NONPOLAR, 153),
                ChemicalBond(4, 5, 1, BondType.COVALENT_NONPOLAR, 153),
                ChemicalBond(5, 0, 1, BondType.COVALENT_NONPOLAR, 153)
            ),
            lewisNotes = "Aldohexose existing primarily as a 6-membered cyclic glucopyranose hemiacetal ring.",
            description = "Blood sugar. Primary cellular energy currency for all living organisms, produced by plant photosynthesis.",
            safetyInfo = "Essential non-toxic biological nutrient.",
            bondExplanation = "High water solubility is enabled by five equatorial hydroxyl groups forming multiple hydrogen bonds with water.",
            pubchemCid = 5793,
            casNumber = "50-99-7"
        ),
        Compound(
            id = "fructose",
            name = "D-Fructose",
            iupacName = "1,3,4,5,6-Pentahydroxyhexan-2-one",
            formula = "C₆H₁₂O₆",
            category = "Biomolecule / Monosaccharide",
            molarMass = 180.16,
            geometry = "Furanose Ring (Envelope Conformation)",
            bondAngles = "109.5°",
            polarity = "Highly Polar",
            functionalGroups = listOf("Ketohexose", "Hydroxyl (-OH)"),
            atoms = listOf(
                Atom3D("C", 0f, 0f, 0f, 0xFF475569, 1.0f),
                Atom3D("C", 1.3f, 0f, 0f, 0xFF475569, 1.0f),
                Atom3D("O", 1.8f, 1.1f, 0f, 0xFFEF4444, 1.1f),
                Atom3D("C", 0.7f, 1.9f, 0f, 0xFF475569, 1.0f),
                Atom3D("C", -0.7f, 1.1f, 0f, 0xFF475569, 1.0f)
            ),
            bonds = listOf(
                ChemicalBond(0, 1, 1, BondType.COVALENT_NONPOLAR, 153),
                ChemicalBond(1, 2, 1, BondType.COVALENT_POLAR, 143),
                ChemicalBond(2, 3, 1, BondType.COVALENT_POLAR, 143),
                ChemicalBond(3, 4, 1, BondType.COVALENT_NONPOLAR, 153),
                ChemicalBond(4, 0, 1, BondType.COVALENT_NONPOLAR, 153)
            ),
            lewisNotes = "Ketohexose structural isomer of glucose that forms a 5-membered furanose ring.",
            description = "Fruit sugar. The sweetest of all naturally occurring carbohydrates; found in fruits, honey, and high-fructose corn syrup.",
            safetyInfo = "Non-toxic food nutrient; high dietary intake is linked to metabolic syndrome.",
            bondExplanation = "Tautomerizes to glucose under alkaline conditions (Lobry de Bruyn-van Ekenstein transformation).",
            pubchemCid = 5984,
            casNumber = "57-48-7"
        ),
        Compound(
            id = "sucrose",
            name = "Sucrose",
            iupacName = "(2R,3R,4S,5S,6R)-2-[(2S,3S,4S,5R)-3,4-dihydroxy-2,5-bis(hydroxymethyl)oxolan-2-yl]oxy-6-(hydroxymethyl)oxane-3,4,5-triol",
            formula = "C₁₂H₂₂O₁₁",
            category = "Biomolecule / Disaccharide",
            molarMass = 342.3,
            geometry = "Two Linked Rings via Glycosidic Bond",
            bondAngles = "111.0° (C-O-C bridge)",
            polarity = "Highly Polar",
            functionalGroups = listOf("Glycosidic Bond", "Acetal"),
            atoms = listOf(
                Atom3D("C", -1.2f, 0f, 0f, 0xFF475569, 1.0f),
                Atom3D("O", 0f, 0.4f, 0f, 0xFFEF4444, 1.1f),
                Atom3D("C", 1.2f, 0f, 0f, 0xFF475569, 1.0f)
            ),
            bonds = listOf(
                ChemicalBond(0, 1, 1, BondType.COVALENT_POLAR, 142),
                ChemicalBond(1, 2, 1, BondType.COVALENT_POLAR, 142)
            ),
            lewisNotes = "Non-reducing disaccharide composed of α-D-glucopyranose and β-D-fructofuranose linked by an α(1→2)β bond.",
            description = "Common table sugar extracted from sugarcane and sugar beets. Negative to Tollens' and Fehling's tests.",
            safetyInfo = "Edible food substance; major dietary calorie source.",
            bondExplanation = "Both anomeric carbons participate in the glycosidic bond, locking them and preventing mutarotation.",
            pubchemCid = 5988,
            casNumber = "57-50-1"
        ),
        Compound(
            id = "aspirin",
            name = "Aspirin",
            iupacName = "2-Acetoxybenzoic Acid",
            formula = "C₉H₈O₄",
            category = "Pharmaceutical / NSAID",
            molarMass = 180.16,
            geometry = "Planar Ring with Carboxyl & Ester Sidechains",
            bondAngles = "120.0°",
            polarity = "Polar",
            functionalGroups = listOf("Carboxylic Acid (-COOH)", "Ester (-OCOCH₃)"),
            atoms = listOf(
                Atom3D("C", 0f, 0f, 0f, 0xFF475569, 1.0f),
                Atom3D("C", 1.4f, 0f, 0f, 0xFF475569, 1.0f),
                Atom3D("O", 1.9f, 1.1f, 0f, 0xFFEF4444, 1.1f),
                Atom3D("O", 1.9f, -1.1f, 0f, 0xFFEF4444, 1.1f),
                Atom3D("O", -0.7f, 1.1f, 0f, 0xFFEF4444, 1.1f),
                Atom3D("C", -1.9f, 0.7f, 0f, 0xFF475569, 1.0f)
            ),
            bonds = listOf(
                ChemicalBond(0, 1, 1, BondType.COVALENT_NONPOLAR, 148),
                ChemicalBond(1, 2, 2, BondType.COVALENT_POLAR, 121),
                ChemicalBond(1, 3, 1, BondType.COVALENT_POLAR, 135),
                ChemicalBond(0, 4, 1, BondType.COVALENT_POLAR, 137),
                ChemicalBond(4, 5, 1, BondType.COVALENT_POLAR, 136)
            ),
            lewisNotes = "Synthesized by acetylation of salicylic acid with acetic anhydride.",
            description = "Acetylsalicylic acid. World's most famous analgesic, antipyretic, anti-inflammatory, and daily cardioprotective blood thinner.",
            safetyInfo = "Causes gastric irritation and ulcers with prolonged high doses; avoid in children with viral infections (Reye syndrome).",
            bondExplanation = "Irreversibly inhibits cyclooxygenase enzymes (COX-1 and COX-2) by transferring its acetyl group to a serine residue.",
            pubchemCid = 2244,
            casNumber = "50-78-2"
        ),
        Compound(
            id = "paracetamol",
            name = "Paracetamol (Acetaminophen)",
            iupacName = "N-(4-Hydroxyphenyl)ethanamide",
            formula = "C₈H₉NO₂",
            category = "Pharmaceutical / Analgesic",
            molarMass = 151.16,
            geometry = "Planar Ring with Amide & Phenolic OH",
            bondAngles = "120.0°",
            polarity = "Polar",
            functionalGroups = listOf("Phenolic (-OH)", "Secondary Amide (-CONH-)"),
            atoms = listOf(
                Atom3D("C", 0f, 0f, 0f, 0xFF475569, 1.0f),
                Atom3D("N", 1.4f, 0f, 0f, 0xFF3B82F6, 1.1f),
                Atom3D("C", 2.2f, 1.1f, 0f, 0xFF475569, 1.0f),
                Atom3D("O", 3.4f, 1.1f, 0f, 0xFFEF4444, 1.1f),
                Atom3D("C", 1.5f, 2.4f, 0f, 0xFF475569, 1.0f),
                Atom3D("O", -2.8f, 0f, 0f, 0xFFEF4444, 1.1f)
            ),
            bonds = listOf(
                ChemicalBond(0, 1, 1, BondType.COVALENT_POLAR, 142),
                ChemicalBond(1, 2, 1, BondType.COVALENT_POLAR, 136),
                ChemicalBond(2, 3, 2, BondType.COVALENT_POLAR, 123),
                ChemicalBond(2, 4, 1, BondType.COVALENT_NONPOLAR, 151)
            ),
            lewisNotes = "Amide group and phenolic hydroxyl group situated para to each other on the benzene ring.",
            description = "Widely used over-the-counter pain reliever and fever reducer (Tylenol / Panadol).",
            safetyInfo = "Acute hepatotoxicity hazard in overdose via accumulation of toxic NAPQI metabolite.",
            bondExplanation = "Acts predominantly within the central nervous system to inhibit prostaglandin synthesis and reduce fever.",
            pubchemCid = 1983,
            casNumber = "103-90-2"
        ),
        Compound(
            id = "caffeine",
            name = "Caffeine",
            iupacName = "1,3,7-Trimethylpurine-2,6-dione",
            formula = "C₈H₁₀N₄O₂",
            category = "Alkaloid / Stimulant",
            molarMass = 194.19,
            geometry = "Planar Fused Bicyclic Purine Ring",
            bondAngles = "120.0° (Ring)",
            polarity = "Polar (Dipole Moment: 3.64 D)",
            functionalGroups = listOf("Purine Alkaloid", "Amide / Imide"),
            atoms = listOf(
                Atom3D("C", 0f, 0f, 0f, 0xFF475569, 1.0f),
                Atom3D("N", 1.3f, 0.4f, 0f, 0xFF3B82F6, 1.1f),
                Atom3D("C", 1.3f, 1.8f, 0f, 0xFF475569, 1.0f),
                Atom3D("O", 2.3f, 2.5f, 0f, 0xFFEF4444, 1.1f),
                Atom3D("N", 0f, 2.3f, 0f, 0xFF3B82F6, 1.1f),
                Atom3D("C", -1.1f, 1.3f, 0f, 0xFF475569, 1.0f)
            ),
            bonds = listOf(
                ChemicalBond(0, 1, 1, BondType.COVALENT_POLAR, 138),
                ChemicalBond(1, 2, 1, BondType.COVALENT_POLAR, 138),
                ChemicalBond(2, 3, 2, BondType.COVALENT_POLAR, 122),
                ChemicalBond(2, 4, 1, BondType.COVALENT_POLAR, 138),
                ChemicalBond(4, 5, 1, BondType.COVALENT_POLAR, 138),
                ChemicalBond(5, 0, 2, BondType.COVALENT_NONPOLAR, 136)
            ),
            lewisNotes = "Fused pyrimidinedione and imidazole ring with three methyl groups on nitrogens.",
            description = "The world's most widely consumed central nervous system psychoactive stimulant; found in coffee beans and tea leaves.",
            safetyInfo = "Safe in moderate dietary intake; high doses cause tachycardia, tremors, and insomnia.",
            bondExplanation = "Molecular shape resembles adenosine, allowing competitive antagonism of adenosine receptors to ward off drowsiness.",
            pubchemCid = 2519,
            casNumber = "58-08-2"
        ),
        Compound(
            id = "glycerol",
            name = "Glycerol (Glycerin)",
            iupacName = "Propane-1,2,3-triol",
            formula = "C₃H₈O₃",
            category = "Organic Polyol / Triol",
            molarMass = 92.09,
            geometry = "Flexible Hydrocarbon Chain with Three OH Groups",
            bondAngles = "109.5°",
            polarity = "Extremely Polar (Hydrophilic)",
            functionalGroups = listOf("Triol (Three -OH groups)"),
            atoms = listOf(
                Atom3D("C", -1.27f, 0f, 0f, 0xFF475569, 1.0f),
                Atom3D("C", 0f, 0.6f, 0f, 0xFF475569, 1.0f),
                Atom3D("C", 1.27f, 0f, 0f, 0xFF475569, 1.0f),
                Atom3D("O", -1.27f, -1.4f, 0f, 0xFFEF4444, 1.1f),
                Atom3D("O", 0f, 2.0f, 0f, 0xFFEF4444, 1.1f),
                Atom3D("O", 1.27f, -1.4f, 0f, 0xFFEF4444, 1.1f)
            ),
            bonds = listOf(
                ChemicalBond(0, 1, 1, BondType.COVALENT_NONPOLAR, 154),
                ChemicalBond(1, 2, 1, BondType.COVALENT_NONPOLAR, 154),
                ChemicalBond(0, 3, 1, BondType.COVALENT_POLAR, 143),
                ChemicalBond(1, 4, 1, BondType.COVALENT_POLAR, 143),
                ChemicalBond(2, 5, 1, BondType.COVALENT_POLAR, 143)
            ),
            lewisNotes = "Three-carbon chain bearing three hydroxyl groups; esterifies with fatty acids to form triglycerides (fats).",
            description = "Viscous, sweet-tasting, non-toxic liquid used as a humectant in pharmaceuticals, cosmetics, and food.",
            safetyInfo = "Non-hazardous food grade ingredient.",
            bondExplanation = "Three -OH groups create extensive 3D hydrogen bonding networks, resulting in high viscosity and boiling point (290°C).",
            pubchemCid = 753,
            casNumber = "56-81-5"
        )
    )
}
