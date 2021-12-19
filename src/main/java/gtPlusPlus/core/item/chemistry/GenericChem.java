package gtPlusPlus.core.item.chemistry;

import gregtech.api.enums.*;
import gregtech.api.util.GT_ModHandler;
import gregtech.api.util.GT_Utility;
import gtPlusPlus.api.objects.minecraft.ItemPackage;
import gtPlusPlus.core.item.chemistry.general.ItemGenericChemBase;
import gtPlusPlus.core.item.circuit.ItemAdvancedChip;
import gtPlusPlus.core.lib.CORE;
import gtPlusPlus.core.material.*;
import gtPlusPlus.core.material.state.MaterialState;
import gtPlusPlus.core.recipe.common.CI;
import gtPlusPlus.core.util.Utils;
import gtPlusPlus.core.util.minecraft.FluidUtils;
import gtPlusPlus.core.util.minecraft.ItemUtils;
import gtPlusPlus.core.util.minecraft.MaterialUtils;
import gtPlusPlus.plugin.agrichem.BioRecipes;
import gtPlusPlus.plugin.agrichem.block.AgrichemFluids;
import gtPlusPlus.xmod.gregtech.api.enums.GregtechItemList;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.*;

public class GenericChem extends ItemPackage {

	/**
	 * Switches
	 */

	private static boolean usingGregtechNitricOxide = false;
	private static boolean usingGregtechNitrogenDioxide = false;
	private static boolean usingGregtechHydricSulfur = false;

	/**
	 * Materials
	 */	

	//public static final Material BAKELITE = new Material("Bakelite", MaterialState.SOLID, TextureSet.SET_DULL, new short[]{90, 140, 140}, 120, 240, 23, 24, true, null, 0);//Not a GT Inherited Material
	//public static final Material NYLON = new Material("Nylon", MaterialState.SOLID, TextureSet.SET_SHINY, new short[]{45, 45, 45}, 300, 600, 44, 48, true, null, 0);//Not a GT Inherited Material
	//public static final Material CARBYNE = new Material("Carbyne", MaterialState.SOLID, TextureSet.SET_DULL, new short[]{25, 25, 25}, 2500, 5000, 63, 52, true, null, 0);//Not a GT Inherited Material


	//Refined PTFE
	public static final Material TEFLON = new Material(
			"Teflon",
			MaterialState.SOLID,
			TextureSet.SET_SHINY,
			new short[] { 75, 45, 75 },
			330, 640,
			-1, -1,
			false,
			null,
			0,
			new MaterialStack[] {
					new MaterialStack(NONMATERIAL.PTFE, 75),
					new MaterialStack(NONMATERIAL.PLASTIC, 15),
					new MaterialStack(ELEMENT.getInstance().CARBON, 5),
					new MaterialStack(ELEMENT.getInstance().SODIUM, 5)
			});

	/**
	 * Fluids
	 */

	public static Fluid Benzene;
	public static Fluid NitroBenzene;
	public static Fluid Aniline;
	public static Fluid Polyurethane;
	public static Fluid Phenol; //https://en.wikipedia.org/wiki/Phenol#Uses
	public static Fluid Cyclohexane; //https://en.wikipedia.org/wiki/Cyclohexane	
	public static Fluid Cyclohexanone; //https://en.wikipedia.org/wiki/Cyclohexanone	
	public static Fluid Cadaverine; //https://en.wikipedia.org/wiki/Cadaverine
	public static Fluid Putrescine; //https://en.wikipedia.org/wiki/Putrescine
	public static Fluid BoricAcid;
	public static Fluid HydrochloricAcid;


	public static Fluid Ethylanthraquinone2;
	public static Fluid Ethylanthrahydroquinone2;
	public static Fluid Hydrogen_Peroxide;
	public static Fluid Lithium_Peroxide;
	public static Fluid Nitric_Oxide;
	public static Fluid Nitrogen_Dioxide;
	public static Fluid Carbon_Disulfide;
	public static Fluid Hydrogen_Sulfide;

	/**
	 * Items
	 */

	// Phenol Byproducts
	public Item PhenolicResins; //https://en.wikipedia.org/wiki/Phenol_formaldehyde_resin
	public static ItemGenericChemBase mGenericChemItem1;
	public static Item mAdvancedCircuit;

	private ItemStack mCatalystCarrier;

	public static ItemStack mRedCatalyst;
	public static ItemStack mYellowCatalyst;
	public static ItemStack mBlueCatalyst;
	public static ItemStack mOrangeCatalyst;
	public static ItemStack mPurpleCatalyst;
	public static ItemStack mBrownCatalyst;
	public static ItemStack mPinkCatalyst;
	public static ItemStack mFormaldehydeCatalyst;

	public static ItemStack mMillingBallAlumina;
	public static ItemStack mMillingBallSoapstone;
	
	public static ItemStack mSodiumEthoxide;
	public static ItemStack mSodiumEthylXanthate;
	public static ItemStack mPotassiumEthylXanthate;
	public static ItemStack mPotassiumHydroxide;



	@Override
	public void items() {		
		PhenolicResins = ItemUtils.generateSpecialUseDusts("phenolicresins", "Phenolic Resin", "HOC6H4CH2OH", Utils.rgbtoHexValue(80, 40, 40))[0];		
		//MaterialGenerator.generate(BAKELITE, false);	
		//MaterialGenerator.generate(NYLON, false);
		MaterialGenerator.generate(TEFLON, false);		

		mGenericChemItem1 = new ItemGenericChemBase();	
		mAdvancedCircuit = new ItemAdvancedChip();		
		GregtechItemList.Circuit_T3RecipeSelector.set(mAdvancedCircuit);

		registerItemStacks();
		registerOreDict();

		GregtechItemList.Milling_Ball_Alumina.set(mMillingBallAlumina);
		GregtechItemList.Milling_Ball_Soapstone.set(mMillingBallSoapstone);

	}


	public void registerItemStacks() {

		mCatalystCarrier = ItemUtils.simpleMetaStack(AgriculturalChem.mAgrichemItem1, 13, 1);

		mRedCatalyst = ItemUtils.simpleMetaStack(mGenericChemItem1, 0, 1);
		mYellowCatalyst = ItemUtils.simpleMetaStack(mGenericChemItem1, 1, 1);
		mBlueCatalyst = ItemUtils.simpleMetaStack(mGenericChemItem1, 2, 1);
		mOrangeCatalyst = ItemUtils.simpleMetaStack(mGenericChemItem1, 3, 1);
		mPurpleCatalyst = ItemUtils.simpleMetaStack(mGenericChemItem1, 4, 1);
		mBrownCatalyst = ItemUtils.simpleMetaStack(mGenericChemItem1, 5, 1);
		mPinkCatalyst = ItemUtils.simpleMetaStack(mGenericChemItem1, 6, 1);
		mMillingBallAlumina = ItemUtils.simpleMetaStack(mGenericChemItem1, 7, 1);
		mMillingBallSoapstone = ItemUtils.simpleMetaStack(mGenericChemItem1, 8, 1);
		mSodiumEthoxide = ItemUtils.simpleMetaStack(mGenericChemItem1, 9, 1);
		mSodiumEthylXanthate = ItemUtils.simpleMetaStack(mGenericChemItem1, 10, 1);
		mPotassiumEthylXanthate = ItemUtils.simpleMetaStack(mGenericChemItem1, 11, 1);
		mPotassiumHydroxide = ItemUtils.simpleMetaStack(mGenericChemItem1, 12, 1);
		mFormaldehydeCatalyst = ItemUtils.simpleMetaStack(mGenericChemItem1, 13, 1);

	}

	public void registerOreDict() {

		ItemUtils.addItemToOreDictionary(mRedCatalyst, "catalystIronCopper");
		ItemUtils.addItemToOreDictionary(mYellowCatalyst, "catalystTungstenNickel");
		ItemUtils.addItemToOreDictionary(mBlueCatalyst, "catalystCobaltTitanium");
		ItemUtils.addItemToOreDictionary(mOrangeCatalyst, "catalystVanadiumPalladium");
		ItemUtils.addItemToOreDictionary(mPurpleCatalyst, "catalystIridiumRuthenium");
		ItemUtils.addItemToOreDictionary(mBrownCatalyst, "catalystNickelAluminium");
		ItemUtils.addItemToOreDictionary(mPinkCatalyst, "catalystPlatinumRhodium");
		ItemUtils.addItemToOreDictionary(mMillingBallAlumina, "millingballAlumina");
		ItemUtils.addItemToOreDictionary(mMillingBallSoapstone, "millingballSoapstone");
		ItemUtils.addItemToOreDictionary(mSodiumEthoxide, "dustSodiumEthoxide");
		ItemUtils.addItemToOreDictionary(mSodiumEthylXanthate, "dustSodiumEthylXanthate");
		ItemUtils.addItemToOreDictionary(mPotassiumEthylXanthate, "dustPotassiumEthylXanthate");
		ItemUtils.addItemToOreDictionary(mPotassiumHydroxide, "dustPotassiumHydroxide");
		ItemUtils.addItemToOreDictionary(mFormaldehydeCatalyst, "catalystFormaldehyde");

	}

	@Override
	public void blocks() {}

	@Override
	public void fluids() {

		if (!FluidRegistry.isFluidRegistered("benzene")) {
			Benzene = FluidUtils.generateFluidNoPrefix("benzene", "Benzene", 278,	new short[] { 100, 70, 30, 100 }, true);			
		}
		else {
			Benzene = FluidRegistry.getFluid("benzene");
		}

		NitroBenzene = FluidUtils.generateFluidNoPrefix("nitrobenzene", "NitroBenzene", 278,	new short[] { 70, 50, 40, 100 }, true);

		Aniline = FluidUtils.generateFluidNoPrefix("aniline", "Aniline", 266,	new short[] { 100, 100, 30, 100 }, true);

		BoricAcid = FluidUtils.generateFluidNoPrefix("boricacid", "Boric Acid", 278, new short[] { 90, 30, 120, 100 }, true);

		Polyurethane = FluidUtils.generateFluidNoPrefix("polyurethane", "Polyurethane", 350,	new short[] { 100, 70, 100, 100 }, true);

		if (!FluidRegistry.isFluidRegistered("phenol")) {
			Phenol = FluidUtils.generateFluidNoPrefix("phenol", "Phenol", 313,	new short[] { 100, 70, 30, 100 }, true);			
		}
		else {
			Phenol = FluidRegistry.getFluid("phenol");
		}	

		// Use GT's if it exists, else make our own.
		if (FluidRegistry.isFluidRegistered("hydrochloricacid_gt5u")) {
			HydrochloricAcid = FluidRegistry.getFluid("hydrochloricacid_gt5u");	
		}
		else {
			HydrochloricAcid = FluidUtils.generateFluidNoPrefix("hydrochloricacid", "Hydrochloric Acid", 285,	new short[] { 183, 200, 196, 100 }, true);
		}	

		Cyclohexane = FluidUtils.generateFluidNoPrefix("cyclohexane", "Cyclohexane", 32 + 175,	new short[] { 100, 70, 30, 100 }, true);		
		Cyclohexanone = FluidUtils.generateFluidNoPrefix("cyclohexanone", "Cyclohexanone", 32 + 175,	new short[] { 100, 70, 30, 100 }, true);

		Cadaverine = FluidUtils.generateFluidNoPrefix("cadaverine", "Cadaverine", 32 + 175,	new short[] { 100, 70, 30, 100 }, true);
		Putrescine = FluidUtils.generateFluidNoPrefix("putrescine", "Putrescine", 32 + 175,	new short[] { 100, 70, 30, 100 }, true);

		//Create 2-Ethylanthraquinone
		//2-Ethylanthraquinone is prepared from the reaction of phthalic anhydride and ethylbenzene
		Ethylanthraquinone2 = FluidUtils.generateFluidNonMolten("2Ethylanthraquinone", "2-Ethylanthraquinone", 415, new short[]{227, 255, 159, 100}, null, null);
		//Create 2-Ethylanthrahydroquinone
		//Palladium plate + Hydrogen(250) + 2-Ethylanthraquinone(500) = 600 Ethylanthrahydroquinone
		Ethylanthrahydroquinone2 = FluidUtils.generateFluidNonMolten("2Ethylanthrahydroquinone", "2-Ethylanthrahydroquinone", 415, new short[]{207, 225, 129, 100}, null, null);
		//Create Hydrogen Peroxide
		//Compressed Air(1500) + Ethylanthrahydroquinone(500) + Anthracene(5) = 450 Ethylanthraquinone && 200 Peroxide
		Hydrogen_Peroxide = FluidUtils.generateFluidNonMolten("HydrogenPeroxide", "Hydrogen Peroxide", 150, new short[]{210, 255, 255, 100}, null, null);

		if (FluidRegistry.isFluidRegistered("nitricoxide")) {
			Nitric_Oxide = FluidRegistry.getFluid("nitricoxide");
			usingGregtechNitricOxide = true;
		}
		else {
			Nitric_Oxide = FluidUtils.generateFluidNoPrefix("nitricoxide", "Nitric Oxide", 200, new short[] {125, 200, 240, 100});
		}
		if (FluidRegistry.isFluidRegistered("nitrogendioxide")) {
			Nitrogen_Dioxide = FluidRegistry.getFluid("nitrogendioxide");
			usingGregtechNitrogenDioxide = true;
		}
		else {
			Nitrogen_Dioxide = FluidUtils.generateFluidNoPrefix("nitrogendioxide", "Nitrogen Dioxide", 200, new short[] {100, 175, 255, 100});
		}



		//Lithium Hydroperoxide - LiOH + H2O2 → LiOOH + 2 H2O
		//ItemUtils.generateSpecialUseDusts("LithiumHydroperoxide", "Lithium Hydroperoxide", "HLiO2", Utils.rgbtoHexValue(125, 125, 125));
		// v - Dehydrate
		//Lithium Peroxide - 2 LiOOH → Li2O2 + H2O2 + 2 H2O
		Lithium_Peroxide = FluidUtils.generateFluidNonMolten("LithiumPeroxide", "Lithium Peroxide", 446, new short[]{135, 135, 135, 100}, null, null);

		Carbon_Disulfide = FluidUtils.generateFluidNoPrefix("CarbonDisulfide", "Carbon Disulfide", 350, new short[]{175, 175, 175, 100});

		if (FluidRegistry.isFluidRegistered("fluid.liquid_hydricsulfur") || MaterialUtils.doesMaterialExist("HydricSulfide")) {
			usingGregtechHydricSulfur = true;
			Fluid aFluid = null;
			if (ItemUtils.doesItemListEntryExist("sHydricSulfur")) {
				ItemStack aListItem = ItemUtils.getValueOfItemList("sHydricSulfur", 1, (ItemList) null);
				if (aListItem != null) {
					FluidStack aFluidStack = FluidContainerRegistry.getFluidForFilledItem(aListItem);
					if (aFluidStack != null) {
						aFluid = aFluidStack.getFluid();
					}
					
				}
			}
			if (aFluid == null) {
				aFluid = FluidUtils.getWildcardFluidStack("liquid_hydricsulfur", 1000).getFluid();				
			}
			Hydrogen_Sulfide = aFluid;
		}
		else {
			Hydrogen_Sulfide = FluidUtils.generateFluidNoPrefix("HydrogenSulfide", "Hydrogen Sulfide", 446, new short[]{240, 130, 30, 100});
		}
		
	}	

	@Override
	public String errorMessage() {
		return "Failed to generate recipes for GenericChem.";
	}

	@Override
	public boolean generateRecipes() {

		recipeAdvancedChip();
		recipeCatalystRed();
		recipeCatalystYellow();
		recipeCatalystBlue();
		recipeCatalystOrange();
		recipeCatalystPurple();
		recipeCatalystBrown();
		recipeCatalystPink();
		recipeCatalystFormaldehyde();

		recipeGrindingBallAlumina();
		recipeGrindingBallSoapstone();

		recipeNitroBenzene();
		recipeAniline();
		recipeCadaverineAndPutrescine();
		recipeCyclohexane();
		recipeCyclohexanone();

		recipe2Ethylanthraquinone();
		recipe2Ethylanthrahydroquinone();
		recipeHydrogenPeroxide();
		recipeLithiumHydroperoxide();
		recipeLithiumPeroxide();
		if (!usingGregtechNitricOxide) {
			recipeNitricOxide();	
		}
		if (!usingGregtechNitrogenDioxide) {
			recipeNitrogenDioxide();	
		}
		if (!usingGregtechHydricSulfur) {
			recipeHydricSulfur();			
		}

		// Add recipes if we are not using GT's fluid.
		if (!FluidRegistry.isFluidRegistered("hydrochloricacid_gt5u")) {
			recipeHydrochloricAcid();			
		}
		
		recipeSodiumEthoxide();
		recipeCarbonDisulfide();
		recipeEthylXanthates();
		recipePotassiumHydroxide();
		
		registerFuels();

		return true;
	}	


	private void recipeSodiumEthoxide() {
		//2 C2H5OH + 2 Na → 2 C2H5ONa + H2
		CORE.RA.addChemicalPlantRecipe(
				new ItemStack[] {
						CI.getNumberedCircuit(16),
						ELEMENT.getInstance().SODIUM.getDust(2)
				}, 
				new FluidStack[] {
						FluidUtils.getFluidStack(BioRecipes.mEthanol, 1000),
				}, 
				new ItemStack[] {
						ItemUtils.getSimpleStack(mSodiumEthoxide, 2)

				}, 
				new FluidStack[] {
						ELEMENT.getInstance().HYDROGEN.getFluidStack(2000)						
				}, 
				20 *20,
				120, 
				2);
	}


	private void recipePotassiumHydroxide() {
		//Ca(OH)2 + K2CO3 → CaCO3 + 2 KOH		
		CORE.RA.addChemicalPlantRecipe(
				new ItemStack[] {
						CI.getNumberedCircuit(18),
						ELEMENT.getInstance().POTASSIUM.getDust(4),	
						ELEMENT.getInstance().CARBON.getDust(2),	
						ItemUtils.getItemStackOfAmountFromOreDict("dustCalciumHydroxide", 2),
				}, 
				new FluidStack[] {
						FluidUtils.getFluidStack("oxygen", 6000),
				}, 
				new ItemStack[] {
						ItemUtils.getItemStackOfAmountFromOreDict("dustCalciumCarbonate", 2),
						ItemUtils.getSimpleStack(mPotassiumHydroxide, 4)

				}, 
				new FluidStack[] {
						
				}, 
				20 *30,
				120, 
				2);
		
	}


	private void recipeEthylXanthates() {

		//Potassium ethyl xanthate - CH3CH2OH + CS2 + KOH → CH3CH2OCS2K + H2O
		CORE.RA.addChemicalPlantRecipe(
				new ItemStack[] {
						CI.getNumberedCircuit(17),	
						ItemUtils.getItemStackOfAmountFromOreDict("dustCalciumHydroxide", 2),
				}, 
				new FluidStack[] {
						FluidUtils.getFluidStack(BioRecipes.mEthanol, 1000),
						FluidUtils.getFluidStack(Carbon_Disulfide, 1000),
				}, 
				new ItemStack[] {
						ItemUtils.getSimpleStack(mPotassiumEthylXanthate, 1)
				}, 
				new FluidStack[] {
						FluidUtils.getWater(1000)
				}, 
				20 *60,
				120, 
				4);

		//Sodium ethyl xanthate - CH3CH2ONa + CS2 → CH3CH2OCS2Na
		CORE.RA.addChemicalPlantRecipe(
				new ItemStack[] {
						CI.getNumberedCircuit(17),	
						ItemUtils.getSimpleStack(mSodiumEthoxide, 1)
				}, 
				new FluidStack[] {
						FluidUtils.getFluidStack(Carbon_Disulfide, 1000),
				}, 
				new ItemStack[] {
						ItemUtils.getSimpleStack(mSodiumEthylXanthate, 1)
				}, 
				new FluidStack[] {
						
				}, 
				20 *60,
				120, 
				4);
		
	}


	private void recipeHydricSulfur() {
		
		ItemStack aCellHydricSulfide = ItemUtils.getItemStackOfAmountFromOreDict("cellHydrogenSulfide", 1);		
        GT_Values.RA.addChemicalRecipe(                   ELEMENT.getInstance().SULFUR.getDust(1), GT_Utility.getIntegratedCircuit(1), ELEMENT.getInstance().HYDROGEN.getFluidStack(2000), FluidUtils.getFluidStack(Hydrogen_Sulfide, 3000), GT_Values.NI, 60, 8);
        GT_Values.RA.addChemicalRecipeForBasicMachineOnly(ELEMENT.getInstance().SULFUR.getDust(1), CI.emptyCells(3),        ELEMENT.getInstance().HYDROGEN.getFluidStack(2000), GT_Values.NF, ItemUtils.getSimpleStack(aCellHydricSulfide, 3), GT_Values.NI, 60, 8);
		
	}


	private void recipeCarbonDisulfide() {
		
		CORE.RA.addBlastRecipe(
				new ItemStack[] {
						ItemUtils.getItemStackOfAmountFromOreDict("fuelCoke", 8),
						ItemUtils.getItemStackOfAmountFromOreDict("dustSulfur", 16)						
				}, 
				new FluidStack[] {
						
				}, 
				new ItemStack[] {
						ItemUtils.getItemStackOfAmountFromOreDict("dustDarkAsh", 1)
				}, 
				new FluidStack[] {
						FluidUtils.getFluidStack(Carbon_Disulfide, 4000)
				},
				20 *60 * 10,
				30,
				1500);

		CORE.RA.addChemicalPlantRecipe(
				new ItemStack[] {
						CI.getNumberedCircuit(20),	
						ItemUtils.getSimpleStack(mBrownCatalyst, 0),
						ItemUtils.getItemStackOfAmountFromOreDict("dustSulfur", 4)	
				}, 
				new FluidStack[] {
						FluidUtils.getFluidStack(CoalTar.Coal_Gas, 1000),
				}, 
				new ItemStack[] {

				}, 
				new FluidStack[] {
						FluidUtils.getFluidStack(Carbon_Disulfide, 2000),
						FluidUtils.getFluidStack(Hydrogen_Sulfide, 4000)
				}, 
				20 *60 * 5,
				30, 
				2);
		
		
	}


	private static void registerFuels() {

		// Burnables

		// Gas Fuels
		GT_Values.RA.addFuel(ItemUtils.getItemStackOfAmountFromOreDict("cellNitroBenzene", 1), null, 1250, 1);

	}

	private void recipeGrindingBallAlumina() {
		CORE.RA.addSixSlotAssemblingRecipe(new ItemStack[] {
				CI.getNumberedCircuit(10),
				ItemUtils.getSimpleStack(AgriculturalChem.mAlumina, 64)
		}, 
				FluidUtils.getFluidStack(GenericChem.Aniline, 4000), 
				ItemUtils.getSimpleStack(mMillingBallAlumina, 8),
				180 * 20, 
				480);
	}
	private void recipeGrindingBallSoapstone() {
		CORE.RA.addSixSlotAssemblingRecipe(new ItemStack[] {
				CI.getNumberedCircuit(10),
				ItemUtils.getItemStackOfAmountFromOreDict("dustSoapstone", 32)
		}, 
				FluidUtils.getFluidStack(AgrichemFluids.mLiquidResin, 2500), 
				ItemUtils.getSimpleStack(mMillingBallSoapstone, 8),
				120 * 20, 
				480);
	}

	private void recipeNitrogenDioxide() {
		ItemStack aNitricOxideCell = ItemUtils.getItemStackOfAmountFromOreDict("cellNitricOxide", 1); 
		ItemStack aNitrogenDioxideCell = ItemUtils.getItemStackOfAmountFromOreDict("cellNitrogenDioxide", 1);		
		GT_Values.RA.addChemicalRecipe(                   ItemUtils.getSimpleStack(aNitricOxideCell, 2), GT_Utility.getIntegratedCircuit(1), ELEMENT.getInstance().OXYGEN.getFluidStack(1000),      FluidUtils.getFluidStack(Nitrogen_Dioxide, 3000), CI.emptyCells(2), 160);
		GT_Values.RA.addChemicalRecipe(                   ELEMENT.getInstance().OXYGEN.getCell(1),      GT_Utility.getIntegratedCircuit(1), FluidUtils.getFluidStack(Nitric_Oxide, 2000), FluidUtils.getFluidStack(Nitrogen_Dioxide, 3000), CI.emptyCells(1), 160);
		GT_Values.RA.addChemicalRecipeForBasicMachineOnly(ItemUtils.getSimpleStack(aNitricOxideCell, 2), CI.emptyCells(1),        ELEMENT.getInstance().OXYGEN.getFluidStack(1000),      GT_Values.NF,                           ItemUtils.getSimpleStack(aNitrogenDioxideCell, 3), GT_Values.NI, 160, 30);
		GT_Values.RA.addChemicalRecipeForBasicMachineOnly(ELEMENT.getInstance().OXYGEN.getCell(1),      CI.emptyCells(2),        FluidUtils.getFluidStack(Nitric_Oxide, 2000), GT_Values.NF,                           ItemUtils.getSimpleStack(aNitrogenDioxideCell, 3), GT_Values.NI, 160, 30);
		GT_Values.RA.addChemicalRecipeForBasicMachineOnly(ItemUtils.getSimpleStack(aNitricOxideCell, 2), ELEMENT.getInstance().OXYGEN.getCell(1),       GT_Values.NF,                       GT_Values.NF,                           ItemUtils.getSimpleStack(aNitrogenDioxideCell, 3), GT_Values.NI, 160, 30);
	}


	private void recipeNitricOxide() {
		ItemStack aWaterCell = ItemUtils.getItemStackOfAmountFromOreDict("cellWater", 1);
		ItemStack aNitricOxideCell = ItemUtils.getItemStackOfAmountFromOreDict("cellNitricOxide", 1); 		
		GT_Values.RA.addChemicalRecipeForBasicMachineOnly(MISC_MATERIALS.AMMONIA.getCell(8), CI.emptyCells(1),         ELEMENT.getInstance().OXYGEN.getFluidStack(5000),  FluidUtils.getFluidStack(Nitric_Oxide, 4000), ItemUtils.getSimpleStack(aWaterCell, 9),       GT_Values.NI, 160, 30);
		GT_Values.RA.addChemicalRecipeForBasicMachineOnly(ELEMENT.getInstance().OXYGEN.getCell(5),  CI.emptyCells(4),         MISC_MATERIALS.AMMONIA.getFluidStack(8000), FluidUtils.getFluidStack(Nitric_Oxide, 4000), ItemUtils.getSimpleStack(aWaterCell, 9),       GT_Values.NI, 160, 30);
		GT_Values.RA.addChemicalRecipe(                   MISC_MATERIALS.AMMONIA.getCell(8), GT_Utility.getIntegratedCircuit(11), ELEMENT.getInstance().OXYGEN.getFluidStack(5000),  FluidUtils.getWater(9000),     ItemUtils.getSimpleStack(aNitricOxideCell, 4), CI.emptyCells(4), 160);
		GT_Values.RA.addChemicalRecipe(                   ELEMENT.getInstance().OXYGEN.getCell(5),  GT_Utility.getIntegratedCircuit(11), MISC_MATERIALS.AMMONIA.getFluidStack(8000), FluidUtils.getWater(9000),     ItemUtils.getSimpleStack(aNitricOxideCell, 4), CI.emptyCells(1), 160);
		GT_Values.RA.addChemicalRecipe(                   MISC_MATERIALS.AMMONIA.getCell(8), GT_Utility.getIntegratedCircuit(2),  ELEMENT.getInstance().OXYGEN.getFluidStack(5000),  FluidUtils.getFluidStack(Nitric_Oxide, 4000), CI.emptyCells(8), 320);
		GT_Values.RA.addChemicalRecipe(                   ELEMENT.getInstance().OXYGEN.getCell(5),  GT_Utility.getIntegratedCircuit(2),  MISC_MATERIALS.AMMONIA.getFluidStack(8000), FluidUtils.getFluidStack(Nitric_Oxide, 4000), CI.emptyCells(5), 320);
		GT_Values.RA.addChemicalRecipe(                   MISC_MATERIALS.AMMONIA.getCell(8), GT_Utility.getIntegratedCircuit(12), ELEMENT.getInstance().OXYGEN.getFluidStack(5000),  GT_Values.NF,                       ItemUtils.getSimpleStack(aNitricOxideCell, 4), CI.emptyCells(4), 160);
		GT_Values.RA.addChemicalRecipe(                   ELEMENT.getInstance().OXYGEN.getCell(5),  GT_Utility.getIntegratedCircuit(12), MISC_MATERIALS.AMMONIA.getFluidStack(8000), GT_Values.NF,                       ItemUtils.getSimpleStack(aNitricOxideCell, 4), CI.emptyCells(1), 160);
		GT_Values.RA.addChemicalRecipeForBasicMachineOnly(MISC_MATERIALS.AMMONIA.getCell(8), ELEMENT.getInstance().OXYGEN.getCell(5),        GT_Values.NF,                   GT_Values.NF,                       ItemUtils.getSimpleStack(aNitricOxideCell, 4), ItemUtils.getSimpleStack(aWaterCell, 9), 160, 30);
		GT_Values.RA.addMultiblockChemicalRecipe(new ItemStack[]{GT_Utility.getIntegratedCircuit(1)}, new FluidStack[]{MISC_MATERIALS.AMMONIA.getFluidStack(8000), ELEMENT.getInstance().OXYGEN.getFluidStack(5000)}, new FluidStack[]{FluidUtils.getFluidStack(Nitric_Oxide, 4000), FluidUtils.getWater(9000)}, null, 160, 30);
	}


	private void recipeHydrochloricAcid() {

		ItemStack aAcidCell = ItemUtils.getItemStackOfAmountFromOreDict("cellHydrochloricAcid", 1);

		CORE.RA.addChemicalRecipe(                   
				ELEMENT.getInstance().CHLORINE.getCell(1), 
				GT_Utility.getIntegratedCircuit(1),  
				ELEMENT.getInstance().HYDROGEN.getFluidStack(1000), 
				FluidUtils.getFluidStack(HydrochloricAcid, 2000), 
				CI.emptyCells(1), 
				60, 
				8);

		CORE.RA.addChemicalRecipe(                  
				ELEMENT.getInstance().HYDROGEN.getCell(1),
				GT_Utility.getIntegratedCircuit(1),  
				ELEMENT.getInstance().CHLORINE.getFluidStack(1000), 
				FluidUtils.getFluidStack(HydrochloricAcid, 2000), 
				CI.emptyCells(1), 
				60, 
				8);

		GT_Values.RA.addElectrolyzerRecipe(
				CI.emptyCells(1), 
				GT_Utility.getIntegratedCircuit(1),
				FluidUtils.getFluidStack(HydrochloricAcid, 2000),
				ELEMENT.getInstance().CHLORINE.getFluidStack(1000),
				ELEMENT.getInstance().HYDROGEN.getCell(1), 
				GT_Values.NI,            
				GT_Values.NI, 
				GT_Values.NI,
				GT_Values.NI,
				GT_Values.NI, 
				null, 
				720, 
				30);

		GT_Values.RA.addElectrolyzerRecipe(
				CI.emptyCells(1),            
				GT_Utility.getIntegratedCircuit(11), 
				FluidUtils.getFluidStack(HydrochloricAcid, 2000),        
				ELEMENT.getInstance().HYDROGEN.getFluidStack(1000), 
				ELEMENT.getInstance().CHLORINE.getCell(1),      
				GT_Values.NI,                  
				GT_Values.NI, 
				GT_Values.NI,
				GT_Values.NI, 
				GT_Values.NI, 
				null, 
				720, 
				30);

		GT_Values.RA.addElectrolyzerRecipe(
				ItemUtils.getSimpleStack(aAcidCell, 2), 
				GT_Values.NI,
				GT_Values.NF,
				GT_Values.NF, 
				ELEMENT.getInstance().HYDROGEN.getCell(1),
				ELEMENT.getInstance().CHLORINE.getCell(1),
				GT_Values.NI, 
				GT_Values.NI,
				GT_Values.NI, 
				GT_Values.NI, 
				null, 
				720, 
				30);		
	}

	private void recipeCyclohexane() {

		CORE.RA.addChemicalPlantRecipe(
				new ItemStack[] {
						getTierTwoChip(),		
						ItemUtils.getSimpleStack(mBrownCatalyst, 0)
				}, 
				new FluidStack[] {
						FluidUtils.getFluidStack(Benzene, 2000),
						FluidUtils.getFluidStack("hydrogen", 10000)
				}, 
				new ItemStack[] {

				}, 
				new FluidStack[] {
						FluidUtils.getFluidStack(Cyclohexane, 1000),
				}, 
				20 * 120, 
				120, 
				2);

	}

	private void recipeCyclohexanone() {

		CORE.RA.addChemicalPlantRecipe(
				new ItemStack[] {
						getTierTwoChip(),		
						ItemUtils.getSimpleStack(mBlueCatalyst, 0)
				}, 
				new FluidStack[] {
						FluidUtils.getFluidStack(Cyclohexane, 2000),
						FluidUtils.getFluidStack("air", 10000)
				}, 
				new ItemStack[] {

				}, 
				new FluidStack[] {
						FluidUtils.getFluidStack(Cyclohexanone, 2000),
				},  
				20 * 120, 
				120, 
				2);

		CORE.RA.addChemicalPlantRecipe(
				new ItemStack[] {
						getTierTwoChip(),
				}, 
				new FluidStack[] {
						FluidUtils.getFluidStack(Phenol, 2000),
						FluidUtils.getFluidStack("oxygen", 5000)
				}, 
				new ItemStack[] {

				}, 
				new FluidStack[] {
						FluidUtils.getFluidStack(Cyclohexanone, 2000),
				},  
				20 * 120, 
				120, 
				2);




	}

	private void recipeCatalystRed() {
		// Assembly Recipe
		CORE.RA.addSixSlotAssemblingRecipe(new ItemStack[] {
				getTierOneChip(),
				CI.getEmptyCatalyst(10),
				ELEMENT.getInstance().IRON.getDust(2),
				ELEMENT.getInstance().COPPER.getDust(2),
		}, 
				GT_Values.NF, 
				ItemUtils.getSimpleStack(mRedCatalyst, 10),
				20 * 20, 
				30);

	}

	private void recipeCatalystYellow() {
		// Assembly Recipe
		CORE.RA.addSixSlotAssemblingRecipe(new ItemStack[] {
				getTierThreeChip(),
				CI.getEmptyCatalyst(10),
				ELEMENT.getInstance().TUNGSTEN.getDust(4),
				ELEMENT.getInstance().NICKEL.getDust(4),
		}, 
				GT_Values.NF, 
				ItemUtils.getSimpleStack(mYellowCatalyst, 10),
				60 * 20, 
				2000);

	}

	private void recipeCatalystBlue() {
		// Assembly Recipe
		CORE.RA.addSixSlotAssemblingRecipe(new ItemStack[] {
				getTierTwoChip(),
				CI.getEmptyCatalyst(10),
				ELEMENT.getInstance().COBALT.getDust(3),
				ELEMENT.getInstance().TITANIUM.getDust(3),
		}, 
				GT_Values.NF, 
				ItemUtils.getSimpleStack(mBlueCatalyst, 10),
				40 * 20, 
				500);

	}

	private void recipeCatalystOrange() {
		// Assembly Recipe
		CORE.RA.addSixSlotAssemblingRecipe(new ItemStack[] {
				getTierTwoChip(),
				CI.getEmptyCatalyst(10),
				ELEMENT.getInstance().VANADIUM.getDust(5),
				ELEMENT.getInstance().PALLADIUM.getDust(5),
		}, 
				GT_Values.NF, 
				ItemUtils.getSimpleStack(mOrangeCatalyst, 10),
				40 * 20, 
				500);

	}

	private void recipeCatalystPurple() {
		// Assembly Recipe
		CORE.RA.addSixSlotAssemblingRecipe(new ItemStack[] {
				getTierFourChip(),
				CI.getEmptyCatalyst(10),
				ELEMENT.getInstance().IRIDIUM.getDust(6),
				ELEMENT.getInstance().RUTHENIUM.getDust(6),
		}, 
				GT_Values.NF, 
				ItemUtils.getSimpleStack(mPurpleCatalyst, 10),
				120 * 20, 
				8000);

	}

	private void recipeCatalystBrown() {
		// Assembly Recipe
		CORE.RA.addSixSlotAssemblingRecipe(new ItemStack[] {
				getTierOneChip(),
				CI.getEmptyCatalyst(10),
				ELEMENT.getInstance().NICKEL.getDust(4),
				ELEMENT.getInstance().ALUMINIUM.getDust(4),
		}, 
				GT_Values.NF, 
				ItemUtils.getSimpleStack(mBrownCatalyst, 10),
				15 * 20, 
				30);

	}

	private void recipeCatalystPink() {
		// Assembly Recipe
		CORE.RA.addSixSlotAssemblingRecipe(new ItemStack[] {
				getTierThreeChip(),
				CI.getEmptyCatalyst(10),
				ELEMENT.getInstance().PLATINUM.getDust(4),
				ELEMENT.getInstance().RHODIUM.getDust(4),
		}, 
				GT_Values.NF, 
				ItemUtils.getSimpleStack(mPinkCatalyst, 10),
				30 * 20, 
				2000);

	}
	
	private void recipeCatalystFormaldehyde() {
		// Assembly Recipe
		CORE.RA.addSixSlotAssemblingRecipe(new ItemStack[] {
				getTierThreeChip(),
				CI.getEmptyCatalyst(4),
				ItemUtils.getSimpleStack(RocketFuels.Formaldehyde_Catalyst_Dust, 8)
		}, 
				GT_Values.NF, 
				ItemUtils.getSimpleStack(mFormaldehydeCatalyst, 4),
				30 * 20, 
				240);

	}

	private void recipeCadaverineAndPutrescine() {

		// Basic Recipe
		CORE.RA.addChemicalPlantRecipe(
				new ItemStack[] {
						getTierOneChip(),		
						ItemUtils.getSimpleStack(Items.rotten_flesh, 64)
				}, 
				new FluidStack[] {
						FluidUtils.getHotWater(2000)
				}, 
				new ItemStack[] {

				}, 
				new FluidStack[] {
						FluidUtils.getFluidStack(Cadaverine, 250),
						FluidUtils.getFluidStack(Putrescine, 250),
				}, 
				20 * 120, 
				120, 
				1);

		// Advanced Recipe
		CORE.RA.addChemicalPlantRecipe(
				new ItemStack[] {
						getTierTwoChip(),		
						ItemUtils.getSimpleStack(Items.rotten_flesh, 128),		
						ItemUtils.simpleMetaStack(AgriculturalChem.mAgrichemItem1, 8, 32)
				}, 
				new FluidStack[] {
						FluidUtils.getHotWater(3000)
				}, 
				new ItemStack[] {

				}, 
				new FluidStack[] {
						FluidUtils.getFluidStack(Cadaverine, 750),
						FluidUtils.getFluidStack(Putrescine, 750),
				}, 
				20 * 120, 
				240, 
				2);

	}

	private void recipeAniline() {

		CORE.RA.addChemicalPlantRecipe(
				new ItemStack[] {
						getTierThreeChip(),		
						ItemUtils.getSimpleStack(mBlueCatalyst, 0)
				}, 
				new FluidStack[] {
						FluidUtils.getFluidStack(NitroBenzene, 2000),
						FluidUtils.getFluidStack("hydrogen", 10000)
				}, 
				new ItemStack[] {

				}, 
				new FluidStack[] {
						FluidUtils.getFluidStack(Aniline, 2000),
				}, 
				20 * 30, 
				500, 
				3);

	}

	private void recipeNitroBenzene() {

		CORE.RA.addChemicalPlantRecipe(
				new ItemStack[] {
						getTierThreeChip(),						
				}, 
				new FluidStack[] {
						FluidUtils.getFluidStack(Benzene, 5000),
						FluidUtils.getFluidStack("sulfuricacid", 3000),
						FluidUtils.getFluidStack("nitricacid", 3000),
						FluidUtils.getDistilledWater(10000)
				}, 
				new ItemStack[] {

				}, 
				new FluidStack[] {
						FluidUtils.getFluidStack("dilutedsulfuricacid", 3000),
						FluidUtils.getFluidStack(NitroBenzene, 5000),
				}, 
				20 * 30, 
				500, 
				4);

	}

	private void recipe2Ethylanthraquinone() {

		CORE.RA.addChemicalPlantRecipe(
				new ItemStack[] {
						CI.getNumberedCircuit(4),		
						ItemUtils.getItemStackOfAmountFromOreDict("dustPhthalicAnhydride", 4),				
				}, 
				new FluidStack[] {
						FluidUtils.getFluidStack(CoalTar.Ethylbenzene, 2000),
				}, 
				new ItemStack[] {

				}, 
				new FluidStack[] {
						FluidUtils.getFluidStack(Ethylanthraquinone2, 2000+(144*4)),
				}, 
				20 * 15, 
				120, 
				2);		

		/*GT_Values.RA.addChemicalRecipe(
				ItemUtils.getItemStackOfAmountFromOreDict("dustPhthalicAnhydride", 4),
				ItemUtils.getItemStackOfAmountFromOreDict("cellEthylbenzene", 2),
				null,
				FluidUtils.getFluidStack("fluid.2ethylanthraquinone", 2000+(144*4)),
				ItemUtils.getItemStackOfAmountFromOreDict("cellEmpty", 2),
				20*16);*/

	}

	private void recipe2Ethylanthrahydroquinone() {

		CORE.RA.addChemicalPlantRecipe(
				new ItemStack[] {
						CI.getNumberedCircuit(4),		
						ItemUtils.getSimpleStack(mOrangeCatalyst, 0),			
				}, 
				new FluidStack[] {
						FluidUtils.getFluidStack(Ethylanthraquinone2, 4000),
						FluidUtils.getFluidStack("hydrogen", 2000),
				}, 
				new ItemStack[] {

				}, 
				new FluidStack[] {
						FluidUtils.getFluidStack(Ethylanthrahydroquinone2, 5000),
				}, 
				20 * 40, 
				120, 
				2);	

		/*GT_Values.RA.addChemicalRecipe(
				ItemUtils.getItemStackOfAmountFromOreDict("platePalladium", 0),
				ItemUtils.getItemStackOfAmountFromOreDict("cell2Ethylanthraquinone", 1),
				FluidUtils.getFluidStack("hydrogen", 500),
				FluidUtils.getFluidStack("fluid.2ethylanthrahydroquinone", 1200),
				ItemUtils.getItemStackOfAmountFromOreDict("cellEmpty", 1),
				20*40);*/

	}

	private void recipeLithiumPeroxide() {
		CORE.RA.addDehydratorRecipe(
				new ItemStack[]{
						ItemUtils.getItemStackOfAmountFromOreDict("dustLithiumHydroperoxide", 2),
						ItemUtils.getItemStackOfAmountFromOreDict("cellEmpty", 3)
				}, 
				null, 
				null, 
				new ItemStack[]{
						ItemUtils.getItemStackOfAmountFromOreDict("dustLithiumPeroxide", 1),
						ItemUtils.getItemStackOfAmountFromOreDict("cellHydrogenPeroxide", 1),
						ItemUtils.getItemStackOfAmountFromOreDict("cellWater", 2)
				},
				new int[]{10000, 10000, 10000}, 
				20*100, 
				120);
	}

	private void recipeLithiumHydroperoxide() {

		CORE.RA.addChemicalPlantRecipe(
				new ItemStack[] {
						CI.getNumberedCircuit(4),		
						ItemUtils.getItemStackOfAmountFromOreDict("dustLithiumHydroxide", 7),
				}, 
				new FluidStack[] {
						FluidUtils.getFluidStack("fluid.hydrogenperoxide", 2000),
				}, 
				new ItemStack[] {
						ItemUtils.getItemStackOfAmountFromOreDict("dustLithiumHydroperoxide", 14),						
				}, 
				new FluidStack[] {

				}, 
				20 * 30, 
				240, 
				1);



		/*CORE.RA.addChemicalRecipe(
				ItemUtils.getItemStackOfAmountFromOreDict("dustLithiumHydroxide", 7),
				ItemUtils.getItemStackOfAmountFromOreDict("cellHydrogenPeroxide", 1),
				20,
				FluidUtils.getFluidStack("fluid.cellhydrogenperoxide", 50),
				null,
				ItemUtils.getItemStackOfAmountFromOreDict("dustLithiumHydroperoxide", 14),
				CI.emptyCells(1),
				20*30,
				240);	*/
	}

	private void recipeHydrogenPeroxide() {	

		CORE.RA.addChemicalPlantRecipe(
				new ItemStack[] {
						CI.getNumberedCircuit(4),
				}, 
				new FluidStack[] {
						FluidUtils.getFluidStack("air", 15000),
						FluidUtils.getFluidStack(Ethylanthrahydroquinone2, 5000),
						FluidUtils.getFluidStack("fluid.anthracene", 50),
				}, 
				new ItemStack[] {					
				}, 
				new FluidStack[] {
						FluidUtils.getFluidStack(Ethylanthraquinone2, 4000),
						FluidUtils.getFluidStack("fluid.hydrogenperoxide", 2000),						
				}, 
				20 * 30, 
				240, 
				1);

		/*		CORE.RA.addChemicalRecipe(
						GT_ModHandler.getAirCell(15),
						ItemUtils.getItemStackOfAmountFromOreDict("cell2Ethylanthrahydroquinone", 5),
						20,
						FluidUtils.getFluidStack("fluid.anthracene", 50),
						FluidUtils.getFluidStack("fluid.2ethylanthrahydroquinone", 4450),
						ItemUtils.getItemStackOfAmountFromOreDict("cellHydrogenPeroxide", 2),
						CI.emptyCells(18),
						20*30,
						240);*/

	}






























	private static final ItemStack getTierOneChip() {
		return CI.getNumberedAdvancedCircuit(4);
	}
	private static final ItemStack getTierTwoChip() {
		return CI.getNumberedAdvancedCircuit(8);
	}
	private static final ItemStack getTierThreeChip() {
		return CI.getNumberedAdvancedCircuit(12);
	}
	private static final ItemStack getTierFourChip() {
		return CI.getNumberedAdvancedCircuit(16);
	}

	private static void recipeAdvancedChip() {
		GT_ModHandler.addShapelessCraftingRecipe(
				GregtechItemList.Circuit_T3RecipeSelector.getWithDamage(1L, 0L), 0, new Object[]{OrePrefixes.circuit.get(Materials.Advanced)});

		long bits = 0;
		BioRecipes.addCraftingRecipe(GregtechItemList.Circuit_T3RecipeSelector.getWithDamage(1L, 1L, new Object[0]), bits,
				new Object[]{"d  ", " P ", "   ", 'P', GregtechItemList.Circuit_T3RecipeSelector.getWildcard(1L, new Object[0])});
		BioRecipes.addCraftingRecipe(GregtechItemList.Circuit_T3RecipeSelector.getWithDamage(1L, 2L, new Object[0]), bits,
				new Object[]{" d ", " P ", "   ", 'P', GregtechItemList.Circuit_T3RecipeSelector.getWildcard(1L, new Object[0])});
		BioRecipes.addCraftingRecipe(GregtechItemList.Circuit_T3RecipeSelector.getWithDamage(1L, 3L, new Object[0]), bits,
				new Object[]{"  d", " P ", "   ", 'P', GregtechItemList.Circuit_T3RecipeSelector.getWildcard(1L, new Object[0])});
		BioRecipes.addCraftingRecipe(GregtechItemList.Circuit_T3RecipeSelector.getWithDamage(1L, 4L, new Object[0]), bits,
				new Object[]{"   ", " Pd", "   ", 'P', GregtechItemList.Circuit_T3RecipeSelector.getWildcard(1L, new Object[0])});
		BioRecipes.addCraftingRecipe(GregtechItemList.Circuit_T3RecipeSelector.getWithDamage(1L, 5L, new Object[0]), bits,
				new Object[]{"   ", " P ", "  d", 'P', GregtechItemList.Circuit_T3RecipeSelector.getWildcard(1L, new Object[0])});
		BioRecipes.addCraftingRecipe(GregtechItemList.Circuit_T3RecipeSelector.getWithDamage(1L, 6L, new Object[0]), bits,
				new Object[]{"   ", " P ", " d ", 'P', GregtechItemList.Circuit_T3RecipeSelector.getWildcard(1L, new Object[0])});
		BioRecipes.addCraftingRecipe(GregtechItemList.Circuit_T3RecipeSelector.getWithDamage(1L, 7L, new Object[0]), bits,
				new Object[]{"   ", " P ", "d  ", 'P', GregtechItemList.Circuit_T3RecipeSelector.getWildcard(1L, new Object[0])});
		BioRecipes.addCraftingRecipe(GregtechItemList.Circuit_T3RecipeSelector.getWithDamage(1L, 8L, new Object[0]), bits,
				new Object[]{"   ", "dP ", "   ", 'P', GregtechItemList.Circuit_T3RecipeSelector.getWildcard(1L, new Object[0])});
		BioRecipes.addCraftingRecipe(GregtechItemList.Circuit_T3RecipeSelector.getWithDamage(1L, 9L, new Object[0]), bits,
				new Object[]{"P d", "   ", "   ", 'P', GregtechItemList.Circuit_T3RecipeSelector.getWildcard(1L, new Object[0])});
		BioRecipes.addCraftingRecipe(GregtechItemList.Circuit_T3RecipeSelector.getWithDamage(1L, 10L, new Object[0]), bits,
				new Object[]{"P  ", "  d", "   ", 'P', GregtechItemList.Circuit_T3RecipeSelector.getWildcard(1L, new Object[0])});
		BioRecipes.addCraftingRecipe(GregtechItemList.Circuit_T3RecipeSelector.getWithDamage(1L, 11L, new Object[0]), bits,
				new Object[]{"P  ", "   ", "  d", 'P', GregtechItemList.Circuit_T3RecipeSelector.getWildcard(1L, new Object[0])});
		BioRecipes.addCraftingRecipe(GregtechItemList.Circuit_T3RecipeSelector.getWithDamage(1L, 12L, new Object[0]), bits,
				new Object[]{"P  ", "   ", " d ", 'P', GregtechItemList.Circuit_T3RecipeSelector.getWildcard(1L, new Object[0])});
		BioRecipes.addCraftingRecipe(GregtechItemList.Circuit_T3RecipeSelector.getWithDamage(1L, 13L, new Object[0]), bits,
				new Object[]{"  P", "   ", "  d", 'P', GregtechItemList.Circuit_T3RecipeSelector.getWildcard(1L, new Object[0])});
		BioRecipes.addCraftingRecipe(GregtechItemList.Circuit_T3RecipeSelector.getWithDamage(1L, 14L, new Object[0]), bits,
				new Object[]{"  P", "   ", " d ", 'P', GregtechItemList.Circuit_T3RecipeSelector.getWildcard(1L, new Object[0])});
		BioRecipes.addCraftingRecipe(GregtechItemList.Circuit_T3RecipeSelector.getWithDamage(1L, 15L, new Object[0]), bits,
				new Object[]{"  P", "   ", "d  ", 'P', GregtechItemList.Circuit_T3RecipeSelector.getWildcard(1L, new Object[0])});
		BioRecipes.addCraftingRecipe(GregtechItemList.Circuit_T3RecipeSelector.getWithDamage(1L, 16L, new Object[0]), bits,
				new Object[]{"  P", "d  ", "   ", 'P', GregtechItemList.Circuit_T3RecipeSelector.getWildcard(1L, new Object[0])});
		BioRecipes.addCraftingRecipe(GregtechItemList.Circuit_T3RecipeSelector.getWithDamage(1L, 17L, new Object[0]), bits,
				new Object[]{"   ", "   ", "d P", 'P', GregtechItemList.Circuit_T3RecipeSelector.getWildcard(1L, new Object[0])});
		BioRecipes.addCraftingRecipe(GregtechItemList.Circuit_T3RecipeSelector.getWithDamage(1L, 18L, new Object[0]), bits,
				new Object[]{"   ", "d  ", "  P", 'P', GregtechItemList.Circuit_T3RecipeSelector.getWildcard(1L, new Object[0])});
		BioRecipes.addCraftingRecipe(GregtechItemList.Circuit_T3RecipeSelector.getWithDamage(1L, 19L, new Object[0]), bits,
				new Object[]{"d  ", "   ", "  P", 'P', GregtechItemList.Circuit_T3RecipeSelector.getWildcard(1L, new Object[0])});
		BioRecipes.addCraftingRecipe(GregtechItemList.Circuit_T3RecipeSelector.getWithDamage(1L, 20L, new Object[0]), bits,
				new Object[]{" d ", "   ", "  P", 'P', GregtechItemList.Circuit_T3RecipeSelector.getWildcard(1L, new Object[0])});
		BioRecipes.addCraftingRecipe(GregtechItemList.Circuit_T3RecipeSelector.getWithDamage(1L, 21L, new Object[0]), bits,
				new Object[]{"d  ", "   ", "P  ", 'P', GregtechItemList.Circuit_T3RecipeSelector.getWildcard(1L, new Object[0])});
		BioRecipes.addCraftingRecipe(GregtechItemList.Circuit_T3RecipeSelector.getWithDamage(1L, 22L, new Object[0]), bits,
				new Object[]{" d ", "   ", "P  ", 'P', GregtechItemList.Circuit_T3RecipeSelector.getWildcard(1L, new Object[0])});
		BioRecipes.addCraftingRecipe(GregtechItemList.Circuit_T3RecipeSelector.getWithDamage(1L, 23L, new Object[0]), bits,
				new Object[]{"  d", "   ", "P  ", 'P', GregtechItemList.Circuit_T3RecipeSelector.getWildcard(1L, new Object[0])});
		BioRecipes.addCraftingRecipe(GregtechItemList.Circuit_T3RecipeSelector.getWithDamage(1L, 24L, new Object[0]), bits,
				new Object[]{"   ", "  d", "P  ", 'P', GregtechItemList.Circuit_T3RecipeSelector.getWildcard(1L, new Object[0])});
	}


}