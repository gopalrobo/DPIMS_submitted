package com.smart.cst.afimail;

import com.smart.cst.afimail.attachment.Base;
import com.smart.cst.afimail.member.Member;
import com.smart.cst.afimail.village.Village;

import java.io.Serializable;
import java.util.ArrayList;

public class FGDNewBean implements Serializable {

    public String position;
    public String id;
    public String nameOfSurveyor;
    public String dateOfFGD;
    public String geoTag;
    public String address;
    public String timeStarted;
    public String village;
    public String gramPanchayat;
    public String mandal;
    public String district;
    public String memberName;
    public String sex;
    public String mobile;
    public String idType;
    public String farmSize;
    public String webLink1;
    public String webLink2;
    public String webLink3;
    public String nameVillagesHamlets;
    public String numberOfHouseholds;
    public String averageLandHoldingSize;
    public String largeFarmers;
    public String mediumFarmers;
    public String marginalFarmers;
    public String traditionalLandTitleHolders;
    public String ownLandCultivators;
    public String leasedLandCultivators;
    public String usingDigitalLandRecords;
    public String irrigatedLand;
    public String rainFedLand;
    public String colourOfSoil;
    public String typeOfSoil;
    public String depthOfSoil;
    public String qualityOfSoil;
    public String knowledgeAboutSoilTesting;
    public String soilTested;
    public String soilHealthCard;
    public String Frequency;
    public String rainfall;
    public String monsoons;
    public String irrigationWaterAvailable;
    public String riverLiftIrrigation;
    public String canalIrrigation;
    public String tankIrrigation;
    public String wellIrrigation;
    public String shallowBorewell;
    public String mediumBoreWell;
    public String deepBoreBoreWell;
    public String dieselKerosenePumps;
    public String electricPumps;
    public String solarPumps;
    public String floodIrrigation;
    public String dripIrrigation;
    public String sprinklerIrrigation;
    public String rainGunIrrigation;
    public String majorCropsRabi;
    public String majorCropsSummer;
    public String majorCropsKharif;
    public String rankValuableCrops;
    public String rankLargeVolumeCrops;
    public String tractor;
    public String ifHireCostTractor;
    public String tiller;
    public String ifHireCostTiller;
    public String irrigation;
    public String ifHireCostIrrigation;
    public String harvester;
    public String ifHireCostHarvester;
    public String trasher;
    public String ifHireCostTrasher;
    public String dryer;
    public String ifHireCostDryer;
    public String coldStorageMonthlyRental;
    public String mandalLevelLicensedShop;
    public String districtLevelLicensedShop;
    public String localLicensedShop;
    public String localUnLicensedShop;
    public String researchUniversity;
    public String othersSource;
    public String isLaborEasilyAccessible;
    public String isLaborEasilyAccessibleSpecify;
    public String wageMalePerDay;
    public String wageFeMalePerDay;
    public String informationAgriBestPractices;
    public String whoProvidesGuidance;
    public String trainingsParticipated;
    public String useWeatherInformation;
    public String ifYesWhatSource;
    public String investmentForCropping;
    public String ownKisanCreditCard;
    public String localMoneyLender;
    public String sHGGroups;
    public String inputShops;
    public String traderAggregator;
    public String mFI;
    public String kCC;
    public String localLandCooperative;
    public String normalBank;
    public String othersSourceFinance;
    public String loanRepayment;
    public String dailyMarketNos;
    public String dailyMarketKms;
    public String weeklyHaatNos;
    public String weeklyHaatKms;
    public String blockMandiNos;
    public String blockMandiKms;
    public String districtAPMCNos;
    public String districtAPMCKms;
    public String specialMarketsNos;
    public String specialMarketsKms;
    public String sourceInformationCurrent;
    public String timeOfSale;
    public String storageSystems;
    public String storageItems;
    public String sellCropsFruitsLocalTrader;
    public String sellCropsFruitsMandi;
    public String sellCropsFruitsOthers;
    public String sellCropsFruitsPrice;
    public String sellCropsFruitsPayment;
    public String sellCropsCerealsLocalTrader;
    public String sellCropsCerealsMandi;
    public String sellCropsCerealsPACS;
    public String sellCropsCerealsFPCPG;
    public String sellCropsCerealscompaniesITC;
    public String sellCropsCerealsOthers;
    public String sellCropsCerealsPrice;
    public String sellCropsCerealsPayment;
    public String sellCropsVegetablesLocalTrader;
    public String sellCropsVegetablesTrader;
    public String sellCropsVegetablesProcessingCompanies;
    public String sellCropsVegetablesOthers;
    public String sellCropsVegetablesPrice;
    public String sellCropsVegetablesPayment;

    public String incomeFromCrops;
    public String incomeFromLivestock;
    public String incomeFromAgriLabour;
    public String incomeFromMigrationLabour;
    public String incomeFromService;
    public String incomeFromShopTrade;
    public String threeMealsOfFood;
    public String landPreparation;
    public String seeds;
    public String fertilizer;
    public String pesticides;
    public String irrigationExpan;
    public String weeding;
    public String harvesting;
    public String cleaningSortingPacking;
    public String transportation;
    public String storage;
    public String milkingCow;
    public String sheepGoats;
    public String backyardPoultry;
    public String aquaculturePond;
    public String ifYesCountAquaculturePond;
    public String tractors;
    public String ifYesCountTractors;
    public String tillers;
    public String ifYesCountTillers;
    public String harvesters;
    public String ifYesCountHarvesters;
    public String uploadAudioUrl1;
    public String uploadAudioUrl2;
    public String uploadAudioUrl3;
    public String uploadAudioUrl4;
    public String uploadAudioUrl5;
    public String uploadAudioUrl6;
    public String uploadAudioUrl7;
    public String uploadAudioUrl8;
    public String uploadAudioUrl9;
    public String uploadAudioUrl10;
    public ArrayList<Base> bases1;
    public ArrayList<Base> bases2;
    public ArrayList<Base> bases3;
    public ArrayList<Base> bases4;
    public ArrayList<Base> bases5;
    public ArrayList<Base> bases6;
    public ArrayList<Base> bases7;
    public ArrayList<Base> bases8;
    public ArrayList<Base> bases9;
    public ArrayList<Base> bases10;

    public ArrayList<Member> memberArrayList;
    public ArrayList<Village> villageArrayList;

    public String studentId;

    public FGDNewBean() {
    }

    public FGDNewBean(String nameOfSurveyor, String dateOfFGD, String geoTag, String address, String timeStarted, String village, String gramPanchayat, String mandal, String district, String memberName, String sex, String mobile, String idType, String farmSize, String webLink1, String webLink2, String webLink3, String nameVillagesHamlets, String numberOfHouseholds, String averageLandHoldingSize, String largeFarmers, String mediumFarmers, String marginalFarmers, String traditionalLandTitleHolders, String ownLandCultivators, String leasedLandCultivators, String usingDigitalLandRecords, String irrigatedLand, String rainFedLand, String colourOfSoil, String typeOfSoil, String depthOfSoil, String qualityOfSoil, String knowledgeAboutSoilTesting, String soilTested, String soilHealthCard, String frequency, String rainfall, String monsoons, String irrigationWaterAvailable, String riverLiftIrrigation, String canalIrrigation, String tankIrrigation, String wellIrrigation, String shallowBorewell, String mediumBoreWell, String deepBoreBoreWell, String dieselKerosenePumps, String electricPumps, String solarPumps, String floodIrrigation, String dripIrrigation, String sprinklerIrrigation, String rainGunIrrigation, String majorCropsRabi, String majorCropsSummer, String majorCropsKharif, String rankValuableCrops, String rankLargeVolumeCrops, String tractor, String ifHireCostTractor, String tiller, String ifHireCostTiller, String irrigation, String ifHireCostIrrigation, String harvester, String ifHireCostHarvester, String trasher, String ifHireCostTrasher, String dryer, String ifHireCostDryer, String coldStorageMonthlyRental, String mandalLevelLicensedShop, String districtLevelLicensedShop, String localLicensedShop, String localUnLicensedShop, String researchUniversity, String othersSource, String isLaborEasilyAccessible, String isLaborEasilyAccessibleSpecify, String wageMalePerDay, String wageFeMalePerDay, String informationAgriBestPractices, String whoProvidesGuidance, String trainingsParticipated, String useWeatherInformation, String ifYesWhatSource, String investmentForCropping, String ownKisanCreditCard, String localMoneyLender, String sHGGroups, String inputShops, String traderAggregator, String mFI, String kCC, String localLandCooperative, String normalBank, String othersSourceFinance, String loanRepayment, String dailyMarketNos, String dailyMarketKms, String weeklyHaatNos, String weeklyHaatKms, String blockMandiNos, String blockMandiKms, String districtAPMCNos, String districtAPMCKms, String specialMarketsNos, String specialMarketsKms, String sourceInformationCurrent, String timeOfSale, String storageSystems, String storageItems, String sellCropsFruitsLocalTrader, String sellCropsFruitsMandi, String sellCropsFruitsOthers, String sellCropsFruitsPrice, String sellCropsFruitsPayment, String sellCropsCerealsLocalTrader, String sellCropsCerealsMandi, String sellCropsCerealsPACS, String sellCropsCerealsFPCPG, String sellCropsCerealscompaniesITC, String sellCropsCerealsOthers, String sellCropsCerealsPrice, String sellCropsCerealsPayment, String sellCropsVegetablesLocalTrader, String sellCropsVegetablesTrader, String sellCropsVegetablesProcessingCompanies, String sellCropsVegetablesOthers, String sellCropsVegetablesPrice, String sellCropsVegetablesPayment, String incomeFromCrops, String incomeFromLivestock, String incomeFromAgriLabour, String incomeFromMigrationLabour, String incomeFromService, String incomeFromShopTrade, String threeMealsOfFood, String landPreparation, String seeds, String fertilizer, String pesticides, String irrigationExpan, String weeding, String harvesting, String cleaningSortingPacking, String transportation, String storage, String milkingCow, String sheepGoats, String backyardPoultry, String aquaculturePond, String ifYesCountAquaculturePond, String tractors, String ifYesCountTractors, String tillers, String ifYesCountTillers, String harvesters, String ifYesCountHarvesters, String uploadAudioUrl1, String uploadAudioUrl2, String uploadAudioUrl3, String uploadAudioUrl4, String uploadAudioUrl5, String uploadAudioUrl6, String uploadAudioUrl7, String uploadAudioUrl8, String uploadAudioUrl9, String uploadAudioUrl10, ArrayList<Base> bases1, ArrayList<Base> bases2, ArrayList<Base> bases3, ArrayList<Base> bases4, ArrayList<Base> bases5, ArrayList<Base> bases6, ArrayList<Base> bases7, ArrayList<Base> bases8, ArrayList<Base> bases9, ArrayList<Base> bases10, ArrayList<Member> memberArrayList, ArrayList<Village> villageArrayList) {
        this.nameOfSurveyor = nameOfSurveyor;
        this.dateOfFGD = dateOfFGD;
        this.geoTag = geoTag;
        this.address = address;
        this.timeStarted = timeStarted;
        this.village = village;
        this.gramPanchayat = gramPanchayat;
        this.mandal = mandal;
        this.district = district;
        this.memberName = memberName;
        this.sex = sex;
        this.mobile = mobile;
        this.idType = idType;
        this.farmSize = farmSize;
        this.webLink1 = webLink1;
        this.webLink2 = webLink2;
        this.webLink3 = webLink3;
        this.nameVillagesHamlets = nameVillagesHamlets;
        this.numberOfHouseholds = numberOfHouseholds;
        this.averageLandHoldingSize = averageLandHoldingSize;
        this.largeFarmers = largeFarmers;
        this.mediumFarmers = mediumFarmers;
        this.marginalFarmers = marginalFarmers;
        this.traditionalLandTitleHolders = traditionalLandTitleHolders;
        this.ownLandCultivators = ownLandCultivators;
        this.leasedLandCultivators = leasedLandCultivators;
        this.usingDigitalLandRecords = usingDigitalLandRecords;
        this.irrigatedLand = irrigatedLand;
        this.rainFedLand = rainFedLand;
        this.colourOfSoil = colourOfSoil;
        this.typeOfSoil = typeOfSoil;
        this.depthOfSoil = depthOfSoil;
        this.qualityOfSoil = qualityOfSoil;
        this.knowledgeAboutSoilTesting = knowledgeAboutSoilTesting;
        this.soilTested = soilTested;
        this.soilHealthCard = soilHealthCard;
        Frequency = frequency;
        this.rainfall = rainfall;
        this.monsoons = monsoons;
        this.irrigationWaterAvailable = irrigationWaterAvailable;
        this.riverLiftIrrigation = riverLiftIrrigation;
        this.canalIrrigation = canalIrrigation;
        this.tankIrrigation = tankIrrigation;
        this.wellIrrigation = wellIrrigation;
        this.shallowBorewell = shallowBorewell;
        this.mediumBoreWell = mediumBoreWell;
        this.deepBoreBoreWell = deepBoreBoreWell;
        this.dieselKerosenePumps = dieselKerosenePumps;
        this.electricPumps = electricPumps;
        this.solarPumps = solarPumps;
        this.floodIrrigation = floodIrrigation;
        this.dripIrrigation = dripIrrigation;
        this.sprinklerIrrigation = sprinklerIrrigation;
        this.rainGunIrrigation = rainGunIrrigation;
        this.majorCropsRabi = majorCropsRabi;
        this.majorCropsSummer = majorCropsSummer;
        this.majorCropsKharif = majorCropsKharif;
        this.rankValuableCrops = rankValuableCrops;
        this.rankLargeVolumeCrops = rankLargeVolumeCrops;
        this.tractor = tractor;
        this.ifHireCostTractor = ifHireCostTractor;
        this.tiller = tiller;
        this.ifHireCostTiller = ifHireCostTiller;
        this.irrigation = irrigation;
        this.ifHireCostIrrigation = ifHireCostIrrigation;
        this.harvester = harvester;
        this.ifHireCostHarvester = ifHireCostHarvester;
        this.trasher = trasher;
        this.ifHireCostTrasher = ifHireCostTrasher;
        this.dryer = dryer;
        this.ifHireCostDryer = ifHireCostDryer;
        this.coldStorageMonthlyRental = coldStorageMonthlyRental;
        this.mandalLevelLicensedShop = mandalLevelLicensedShop;
        this.districtLevelLicensedShop = districtLevelLicensedShop;
        this.localLicensedShop = localLicensedShop;
        this.localUnLicensedShop = localUnLicensedShop;
        this.researchUniversity = researchUniversity;
        this.othersSource = othersSource;
        this.isLaborEasilyAccessible = isLaborEasilyAccessible;
        this.isLaborEasilyAccessibleSpecify = isLaborEasilyAccessibleSpecify;
        this.wageMalePerDay = wageMalePerDay;
        this.wageFeMalePerDay = wageFeMalePerDay;
        this.informationAgriBestPractices = informationAgriBestPractices;
        this.whoProvidesGuidance = whoProvidesGuidance;
        this.trainingsParticipated = trainingsParticipated;
        this.useWeatherInformation = useWeatherInformation;
        this.ifYesWhatSource = ifYesWhatSource;
        this.investmentForCropping = investmentForCropping;
        this.ownKisanCreditCard = ownKisanCreditCard;
        this.localMoneyLender = localMoneyLender;
        this.sHGGroups = sHGGroups;
        this.inputShops = inputShops;
        this.traderAggregator = traderAggregator;
        this.mFI = mFI;
        this.kCC = kCC;
        this.localLandCooperative = localLandCooperative;
        this.normalBank = normalBank;
        this.othersSourceFinance = othersSourceFinance;
        this.loanRepayment = loanRepayment;
        this.dailyMarketNos = dailyMarketNos;
        this.dailyMarketKms = dailyMarketKms;
        this.weeklyHaatNos = weeklyHaatNos;
        this.weeklyHaatKms = weeklyHaatKms;
        this.blockMandiNos = blockMandiNos;
        this.blockMandiKms = blockMandiKms;
        this.districtAPMCNos = districtAPMCNos;
        this.districtAPMCKms = districtAPMCKms;
        this.specialMarketsNos = specialMarketsNos;
        this.specialMarketsKms = specialMarketsKms;
        this.sourceInformationCurrent = sourceInformationCurrent;
        this.timeOfSale = timeOfSale;
        this.storageSystems = storageSystems;
        this.storageItems = storageItems;
        this.sellCropsFruitsLocalTrader = sellCropsFruitsLocalTrader;
        this.sellCropsFruitsMandi = sellCropsFruitsMandi;
        this.sellCropsFruitsOthers = sellCropsFruitsOthers;
        this.sellCropsFruitsPrice = sellCropsFruitsPrice;
        this.sellCropsFruitsPayment = sellCropsFruitsPayment;
        this.sellCropsCerealsLocalTrader = sellCropsCerealsLocalTrader;
        this.sellCropsCerealsMandi = sellCropsCerealsMandi;
        this.sellCropsCerealsPACS = sellCropsCerealsPACS;
        this.sellCropsCerealsFPCPG = sellCropsCerealsFPCPG;
        this.sellCropsCerealscompaniesITC = sellCropsCerealscompaniesITC;
        this.sellCropsCerealsOthers = sellCropsCerealsOthers;
        this.sellCropsCerealsPrice = sellCropsCerealsPrice;
        this.sellCropsCerealsPayment = sellCropsCerealsPayment;
        this.sellCropsVegetablesLocalTrader = sellCropsVegetablesLocalTrader;
        this.sellCropsVegetablesTrader = sellCropsVegetablesTrader;
        this.sellCropsVegetablesProcessingCompanies = sellCropsVegetablesProcessingCompanies;
        this.sellCropsVegetablesOthers = sellCropsVegetablesOthers;
        this.sellCropsVegetablesPrice = sellCropsVegetablesPrice;
        this.sellCropsVegetablesPayment = sellCropsVegetablesPayment;
        this.incomeFromCrops = incomeFromCrops;
        this.incomeFromLivestock = incomeFromLivestock;
        this.incomeFromAgriLabour = incomeFromAgriLabour;
        this.incomeFromMigrationLabour = incomeFromMigrationLabour;
        this.incomeFromService = incomeFromService;
        this.incomeFromShopTrade = incomeFromShopTrade;
        this.threeMealsOfFood = threeMealsOfFood;
        this.landPreparation = landPreparation;
        this.seeds = seeds;
        this.fertilizer = fertilizer;
        this.pesticides = pesticides;
        this.irrigationExpan = irrigationExpan;
        this.weeding = weeding;
        this.harvesting = harvesting;
        this.cleaningSortingPacking = cleaningSortingPacking;
        this.transportation = transportation;
        this.storage = storage;
        this.milkingCow = milkingCow;
        this.sheepGoats = sheepGoats;
        this.backyardPoultry = backyardPoultry;
        this.aquaculturePond = aquaculturePond;
        this.ifYesCountAquaculturePond = ifYesCountAquaculturePond;
        this.tractors = tractors;
        this.ifYesCountTractors = ifYesCountTractors;
        this.tillers = tillers;
        this.ifYesCountTillers = ifYesCountTillers;
        this.harvesters = harvesters;
        this.ifYesCountHarvesters = ifYesCountHarvesters;
        this.uploadAudioUrl1 = uploadAudioUrl1;
        this.uploadAudioUrl2 = uploadAudioUrl2;
        this.uploadAudioUrl3 = uploadAudioUrl3;
        this.uploadAudioUrl4 = uploadAudioUrl4;
        this.uploadAudioUrl5 = uploadAudioUrl5;
        this.uploadAudioUrl6 = uploadAudioUrl6;
        this.uploadAudioUrl7 = uploadAudioUrl7;
        this.uploadAudioUrl8 = uploadAudioUrl8;
        this.uploadAudioUrl9 = uploadAudioUrl9;
        this.uploadAudioUrl10 = uploadAudioUrl10;
        this.bases1 = bases1;
        this.bases2 = bases2;
        this.bases3 = bases3;
        this.bases4 = bases4;
        this.bases5 = bases5;
        this.bases6 = bases6;
        this.bases7 = bases7;
        this.bases8 = bases8;
        this.bases9 = bases9;
        this.bases10 = bases10;
        this.memberArrayList = memberArrayList;
        this.villageArrayList = villageArrayList;
    }

    public FGDNewBean(String id, String nameOfSurveyor, String dateOfFGD, String geoTag, String address, String timeStarted, String village, String gramPanchayat, String mandal, String district, String memberName, String sex, String mobile, String idType, String farmSize, String webLink1, String webLink2, String webLink3, String nameVillagesHamlets, String numberOfHouseholds, String averageLandHoldingSize, String largeFarmers, String mediumFarmers, String marginalFarmers, String traditionalLandTitleHolders, String ownLandCultivators, String leasedLandCultivators, String usingDigitalLandRecords, String irrigatedLand, String rainFedLand, String colourOfSoil, String typeOfSoil, String depthOfSoil, String qualityOfSoil, String knowledgeAboutSoilTesting, String soilTested, String soilHealthCard, String frequency, String rainfall, String monsoons, String irrigationWaterAvailable, String riverLiftIrrigation, String canalIrrigation, String tankIrrigation, String wellIrrigation, String shallowBorewell, String mediumBoreWell, String deepBoreBoreWell, String dieselKerosenePumps, String electricPumps, String solarPumps, String floodIrrigation, String dripIrrigation, String sprinklerIrrigation, String rainGunIrrigation, String majorCropsRabi, String majorCropsSummer, String majorCropsKharif, String rankValuableCrops, String rankLargeVolumeCrops, String tractor, String ifHireCostTractor, String tiller, String ifHireCostTiller, String irrigation, String ifHireCostIrrigation, String harvester, String ifHireCostHarvester, String trasher, String ifHireCostTrasher, String dryer, String ifHireCostDryer, String coldStorageMonthlyRental, String mandalLevelLicensedShop, String districtLevelLicensedShop, String localLicensedShop, String localUnLicensedShop, String researchUniversity, String othersSource, String isLaborEasilyAccessible, String isLaborEasilyAccessibleSpecify, String wageMalePerDay, String wageFeMalePerDay, String informationAgriBestPractices, String whoProvidesGuidance, String trainingsParticipated, String useWeatherInformation, String ifYesWhatSource, String investmentForCropping, String ownKisanCreditCard, String localMoneyLender, String sHGGroups, String inputShops, String traderAggregator, String mFI, String kCC, String localLandCooperative, String normalBank, String othersSourceFinance, String loanRepayment, String dailyMarketNos, String dailyMarketKms, String weeklyHaatNos, String weeklyHaatKms, String blockMandiNos, String blockMandiKms, String districtAPMCNos, String districtAPMCKms, String specialMarketsNos, String specialMarketsKms, String sourceInformationCurrent, String timeOfSale, String storageSystems, String storageItems, String sellCropsFruitsLocalTrader, String sellCropsFruitsMandi, String sellCropsFruitsOthers, String sellCropsFruitsPrice, String sellCropsFruitsPayment, String sellCropsCerealsLocalTrader, String sellCropsCerealsMandi, String sellCropsCerealsPACS, String sellCropsCerealsFPCPG, String sellCropsCerealscompaniesITC, String sellCropsCerealsOthers, String sellCropsCerealsPrice, String sellCropsCerealsPayment, String sellCropsVegetablesLocalTrader, String sellCropsVegetablesTrader, String sellCropsVegetablesProcessingCompanies, String sellCropsVegetablesOthers, String sellCropsVegetablesPrice, String sellCropsVegetablesPayment, String incomeFromCrops, String incomeFromLivestock, String incomeFromAgriLabour, String incomeFromMigrationLabour, String incomeFromService, String incomeFromShopTrade, String threeMealsOfFood, String landPreparation, String seeds, String fertilizer, String pesticides, String irrigationExpan, String weeding, String harvesting, String cleaningSortingPacking, String transportation, String storage, String milkingCow, String sheepGoats, String backyardPoultry, String aquaculturePond, String ifYesCountAquaculturePond, String tractors, String ifYesCountTractors, String tillers, String ifYesCountTillers, String harvesters, String ifYesCountHarvesters, String uploadAudioUrl1, String uploadAudioUrl2, String uploadAudioUrl3, String uploadAudioUrl4, String uploadAudioUrl5, String uploadAudioUrl6, String uploadAudioUrl7, String uploadAudioUrl8, String uploadAudioUrl9, String uploadAudioUrl10, ArrayList<Base> bases1, ArrayList<Base> bases2, ArrayList<Base> bases3, ArrayList<Base> bases4, ArrayList<Base> bases5, ArrayList<Base> bases6, ArrayList<Base> bases7, ArrayList<Base> bases8, ArrayList<Base> bases9, ArrayList<Base> bases10, ArrayList<Member> memberArrayList, ArrayList<Village> villageArrayList) {
        this.id = id;
        this.nameOfSurveyor = nameOfSurveyor;
        this.dateOfFGD = dateOfFGD;
        this.geoTag = geoTag;
        this.address = address;
        this.timeStarted = timeStarted;
        this.village = village;
        this.gramPanchayat = gramPanchayat;
        this.mandal = mandal;
        this.district = district;
        this.memberName = memberName;
        this.sex = sex;
        this.mobile = mobile;
        this.idType = idType;
        this.farmSize = farmSize;
        this.webLink1 = webLink1;
        this.webLink2 = webLink2;
        this.webLink3 = webLink3;
        this.nameVillagesHamlets = nameVillagesHamlets;
        this.numberOfHouseholds = numberOfHouseholds;
        this.averageLandHoldingSize = averageLandHoldingSize;
        this.largeFarmers = largeFarmers;
        this.mediumFarmers = mediumFarmers;
        this.marginalFarmers = marginalFarmers;
        this.traditionalLandTitleHolders = traditionalLandTitleHolders;
        this.ownLandCultivators = ownLandCultivators;
        this.leasedLandCultivators = leasedLandCultivators;
        this.usingDigitalLandRecords = usingDigitalLandRecords;
        this.irrigatedLand = irrigatedLand;
        this.rainFedLand = rainFedLand;
        this.colourOfSoil = colourOfSoil;
        this.typeOfSoil = typeOfSoil;
        this.depthOfSoil = depthOfSoil;
        this.qualityOfSoil = qualityOfSoil;
        this.knowledgeAboutSoilTesting = knowledgeAboutSoilTesting;
        this.soilTested = soilTested;
        this.soilHealthCard = soilHealthCard;
        Frequency = frequency;
        this.rainfall = rainfall;
        this.monsoons = monsoons;
        this.irrigationWaterAvailable = irrigationWaterAvailable;
        this.riverLiftIrrigation = riverLiftIrrigation;
        this.canalIrrigation = canalIrrigation;
        this.tankIrrigation = tankIrrigation;
        this.wellIrrigation = wellIrrigation;
        this.shallowBorewell = shallowBorewell;
        this.mediumBoreWell = mediumBoreWell;
        this.deepBoreBoreWell = deepBoreBoreWell;
        this.dieselKerosenePumps = dieselKerosenePumps;
        this.electricPumps = electricPumps;
        this.solarPumps = solarPumps;
        this.floodIrrigation = floodIrrigation;
        this.dripIrrigation = dripIrrigation;
        this.sprinklerIrrigation = sprinklerIrrigation;
        this.rainGunIrrigation = rainGunIrrigation;
        this.majorCropsRabi = majorCropsRabi;
        this.majorCropsSummer = majorCropsSummer;
        this.majorCropsKharif = majorCropsKharif;
        this.rankValuableCrops = rankValuableCrops;
        this.rankLargeVolumeCrops = rankLargeVolumeCrops;
        this.tractor = tractor;
        this.ifHireCostTractor = ifHireCostTractor;
        this.tiller = tiller;
        this.ifHireCostTiller = ifHireCostTiller;
        this.irrigation = irrigation;
        this.ifHireCostIrrigation = ifHireCostIrrigation;
        this.harvester = harvester;
        this.ifHireCostHarvester = ifHireCostHarvester;
        this.trasher = trasher;
        this.ifHireCostTrasher = ifHireCostTrasher;
        this.dryer = dryer;
        this.ifHireCostDryer = ifHireCostDryer;
        this.coldStorageMonthlyRental = coldStorageMonthlyRental;
        this.mandalLevelLicensedShop = mandalLevelLicensedShop;
        this.districtLevelLicensedShop = districtLevelLicensedShop;
        this.localLicensedShop = localLicensedShop;
        this.localUnLicensedShop = localUnLicensedShop;
        this.researchUniversity = researchUniversity;
        this.othersSource = othersSource;
        this.isLaborEasilyAccessible = isLaborEasilyAccessible;
        this.isLaborEasilyAccessibleSpecify = isLaborEasilyAccessibleSpecify;
        this.wageMalePerDay = wageMalePerDay;
        this.wageFeMalePerDay = wageFeMalePerDay;
        this.informationAgriBestPractices = informationAgriBestPractices;
        this.whoProvidesGuidance = whoProvidesGuidance;
        this.trainingsParticipated = trainingsParticipated;
        this.useWeatherInformation = useWeatherInformation;
        this.ifYesWhatSource = ifYesWhatSource;
        this.investmentForCropping = investmentForCropping;
        this.ownKisanCreditCard = ownKisanCreditCard;
        this.localMoneyLender = localMoneyLender;
        this.sHGGroups = sHGGroups;
        this.inputShops = inputShops;
        this.traderAggregator = traderAggregator;
        this.mFI = mFI;
        this.kCC = kCC;
        this.localLandCooperative = localLandCooperative;
        this.normalBank = normalBank;
        this.othersSourceFinance = othersSourceFinance;
        this.loanRepayment = loanRepayment;
        this.dailyMarketNos = dailyMarketNos;
        this.dailyMarketKms = dailyMarketKms;
        this.weeklyHaatNos = weeklyHaatNos;
        this.weeklyHaatKms = weeklyHaatKms;
        this.blockMandiNos = blockMandiNos;
        this.blockMandiKms = blockMandiKms;
        this.districtAPMCNos = districtAPMCNos;
        this.districtAPMCKms = districtAPMCKms;
        this.specialMarketsNos = specialMarketsNos;
        this.specialMarketsKms = specialMarketsKms;
        this.sourceInformationCurrent = sourceInformationCurrent;
        this.timeOfSale = timeOfSale;
        this.storageSystems = storageSystems;
        this.storageItems = storageItems;
        this.sellCropsFruitsLocalTrader = sellCropsFruitsLocalTrader;
        this.sellCropsFruitsMandi = sellCropsFruitsMandi;
        this.sellCropsFruitsOthers = sellCropsFruitsOthers;
        this.sellCropsFruitsPrice = sellCropsFruitsPrice;
        this.sellCropsFruitsPayment = sellCropsFruitsPayment;
        this.sellCropsCerealsLocalTrader = sellCropsCerealsLocalTrader;
        this.sellCropsCerealsMandi = sellCropsCerealsMandi;
        this.sellCropsCerealsPACS = sellCropsCerealsPACS;
        this.sellCropsCerealsFPCPG = sellCropsCerealsFPCPG;
        this.sellCropsCerealscompaniesITC = sellCropsCerealscompaniesITC;
        this.sellCropsCerealsOthers = sellCropsCerealsOthers;
        this.sellCropsCerealsPrice = sellCropsCerealsPrice;
        this.sellCropsCerealsPayment = sellCropsCerealsPayment;
        this.sellCropsVegetablesLocalTrader = sellCropsVegetablesLocalTrader;
        this.sellCropsVegetablesTrader = sellCropsVegetablesTrader;
        this.sellCropsVegetablesProcessingCompanies = sellCropsVegetablesProcessingCompanies;
        this.sellCropsVegetablesOthers = sellCropsVegetablesOthers;
        this.sellCropsVegetablesPrice = sellCropsVegetablesPrice;
        this.sellCropsVegetablesPayment = sellCropsVegetablesPayment;
        this.incomeFromCrops = incomeFromCrops;
        this.incomeFromLivestock = incomeFromLivestock;
        this.incomeFromAgriLabour = incomeFromAgriLabour;
        this.incomeFromMigrationLabour = incomeFromMigrationLabour;
        this.incomeFromService = incomeFromService;
        this.incomeFromShopTrade = incomeFromShopTrade;
        this.threeMealsOfFood = threeMealsOfFood;
        this.landPreparation = landPreparation;
        this.seeds = seeds;
        this.fertilizer = fertilizer;
        this.pesticides = pesticides;
        this.irrigationExpan = irrigationExpan;
        this.weeding = weeding;
        this.harvesting = harvesting;
        this.cleaningSortingPacking = cleaningSortingPacking;
        this.transportation = transportation;
        this.storage = storage;
        this.milkingCow = milkingCow;
        this.sheepGoats = sheepGoats;
        this.backyardPoultry = backyardPoultry;
        this.aquaculturePond = aquaculturePond;
        this.ifYesCountAquaculturePond = ifYesCountAquaculturePond;
        this.tractors = tractors;
        this.ifYesCountTractors = ifYesCountTractors;
        this.tillers = tillers;
        this.ifYesCountTillers = ifYesCountTillers;
        this.harvesters = harvesters;
        this.ifYesCountHarvesters = ifYesCountHarvesters;
        this.uploadAudioUrl1 = uploadAudioUrl1;
        this.uploadAudioUrl2 = uploadAudioUrl2;
        this.uploadAudioUrl3 = uploadAudioUrl3;
        this.uploadAudioUrl4 = uploadAudioUrl4;
        this.uploadAudioUrl5 = uploadAudioUrl5;
        this.uploadAudioUrl6 = uploadAudioUrl6;
        this.uploadAudioUrl7 = uploadAudioUrl7;
        this.uploadAudioUrl8 = uploadAudioUrl8;
        this.uploadAudioUrl9 = uploadAudioUrl9;
        this.uploadAudioUrl10 = uploadAudioUrl10;
        this.bases1 = bases1;
        this.bases2 = bases2;
        this.bases3 = bases3;
        this.bases4 = bases4;
        this.bases5 = bases5;
        this.bases6 = bases6;
        this.bases7 = bases7;
        this.bases8 = bases8;
        this.bases9 = bases9;
        this.bases10 = bases10;
        this.memberArrayList = memberArrayList;
        this.villageArrayList = villageArrayList;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNameOfSurveyor() {
        return nameOfSurveyor;
    }

    public void setNameOfSurveyor(String nameOfSurveyor) {
        this.nameOfSurveyor = nameOfSurveyor;
    }

    public String getDateOfFGD() {
        return dateOfFGD;
    }

    public void setDateOfFGD(String dateOfFGD) {
        this.dateOfFGD = dateOfFGD;
    }

    public String getGeoTag() {
        return geoTag;
    }

    public void setGeoTag(String geoTag) {
        this.geoTag = geoTag;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTimeStarted() {
        return timeStarted;
    }

    public void setTimeStarted(String timeStarted) {
        this.timeStarted = timeStarted;
    }

    public String getVillage() {
        return village;
    }

    public void setVillage(String village) {
        this.village = village;
    }

    public String getGramPanchayat() {
        return gramPanchayat;
    }

    public void setGramPanchayat(String gramPanchayat) {
        this.gramPanchayat = gramPanchayat;
    }

    public String getMandal() {
        return mandal;
    }

    public void setMandal(String mandal) {
        this.mandal = mandal;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getIdType() {
        return idType;
    }

    public void setIdType(String idType) {
        this.idType = idType;
    }

    public String getFarmSize() {
        return farmSize;
    }

    public void setFarmSize(String farmSize) {
        this.farmSize = farmSize;
    }

    public String getWebLink1() {
        return webLink1;
    }

    public void setWebLink1(String webLink1) {
        this.webLink1 = webLink1;
    }

    public String getWebLink2() {
        return webLink2;
    }

    public void setWebLink2(String webLink2) {
        this.webLink2 = webLink2;
    }

    public String getWebLink3() {
        return webLink3;
    }

    public void setWebLink3(String webLink3) {
        this.webLink3 = webLink3;
    }

    public String getNameVillagesHamlets() {
        return nameVillagesHamlets;
    }

    public void setNameVillagesHamlets(String nameVillagesHamlets) {
        this.nameVillagesHamlets = nameVillagesHamlets;
    }

    public String getNumberOfHouseholds() {
        return numberOfHouseholds;
    }

    public void setNumberOfHouseholds(String numberOfHouseholds) {
        this.numberOfHouseholds = numberOfHouseholds;
    }

    public String getAverageLandHoldingSize() {
        return averageLandHoldingSize;
    }

    public void setAverageLandHoldingSize(String averageLandHoldingSize) {
        this.averageLandHoldingSize = averageLandHoldingSize;
    }

    public String getLargeFarmers() {
        return largeFarmers;
    }

    public void setLargeFarmers(String largeFarmers) {
        this.largeFarmers = largeFarmers;
    }

    public String getMediumFarmers() {
        return mediumFarmers;
    }

    public void setMediumFarmers(String mediumFarmers) {
        this.mediumFarmers = mediumFarmers;
    }

    public String getMarginalFarmers() {
        return marginalFarmers;
    }

    public void setMarginalFarmers(String marginalFarmers) {
        this.marginalFarmers = marginalFarmers;
    }

    public String getTraditionalLandTitleHolders() {
        return traditionalLandTitleHolders;
    }

    public void setTraditionalLandTitleHolders(String traditionalLandTitleHolders) {
        this.traditionalLandTitleHolders = traditionalLandTitleHolders;
    }

    public String getOwnLandCultivators() {
        return ownLandCultivators;
    }

    public void setOwnLandCultivators(String ownLandCultivators) {
        this.ownLandCultivators = ownLandCultivators;
    }

    public String getLeasedLandCultivators() {
        return leasedLandCultivators;
    }

    public void setLeasedLandCultivators(String leasedLandCultivators) {
        this.leasedLandCultivators = leasedLandCultivators;
    }

    public String getUsingDigitalLandRecords() {
        return usingDigitalLandRecords;
    }

    public void setUsingDigitalLandRecords(String usingDigitalLandRecords) {
        this.usingDigitalLandRecords = usingDigitalLandRecords;
    }

    public String getIrrigatedLand() {
        return irrigatedLand;
    }

    public void setIrrigatedLand(String irrigatedLand) {
        this.irrigatedLand = irrigatedLand;
    }

    public String getRainFedLand() {
        return rainFedLand;
    }

    public void setRainFedLand(String rainFedLand) {
        this.rainFedLand = rainFedLand;
    }

    public String getColourOfSoil() {
        return colourOfSoil;
    }

    public void setColourOfSoil(String colourOfSoil) {
        this.colourOfSoil = colourOfSoil;
    }

    public String getTypeOfSoil() {
        return typeOfSoil;
    }

    public void setTypeOfSoil(String typeOfSoil) {
        this.typeOfSoil = typeOfSoil;
    }

    public String getDepthOfSoil() {
        return depthOfSoil;
    }

    public void setDepthOfSoil(String depthOfSoil) {
        this.depthOfSoil = depthOfSoil;
    }

    public String getQualityOfSoil() {
        return qualityOfSoil;
    }

    public void setQualityOfSoil(String qualityOfSoil) {
        this.qualityOfSoil = qualityOfSoil;
    }

    public String getKnowledgeAboutSoilTesting() {
        return knowledgeAboutSoilTesting;
    }

    public void setKnowledgeAboutSoilTesting(String knowledgeAboutSoilTesting) {
        this.knowledgeAboutSoilTesting = knowledgeAboutSoilTesting;
    }

    public String getSoilTested() {
        return soilTested;
    }

    public void setSoilTested(String soilTested) {
        this.soilTested = soilTested;
    }

    public String getSoilHealthCard() {
        return soilHealthCard;
    }

    public void setSoilHealthCard(String soilHealthCard) {
        this.soilHealthCard = soilHealthCard;
    }

    public String getFrequency() {
        return Frequency;
    }

    public void setFrequency(String frequency) {
        Frequency = frequency;
    }

    public String getRainfall() {
        return rainfall;
    }

    public void setRainfall(String rainfall) {
        this.rainfall = rainfall;
    }

    public String getMonsoons() {
        return monsoons;
    }

    public void setMonsoons(String monsoons) {
        this.monsoons = monsoons;
    }

    public String getIrrigationWaterAvailable() {
        return irrigationWaterAvailable;
    }

    public void setIrrigationWaterAvailable(String irrigationWaterAvailable) {
        this.irrigationWaterAvailable = irrigationWaterAvailable;
    }

    public String getRiverLiftIrrigation() {
        return riverLiftIrrigation;
    }

    public void setRiverLiftIrrigation(String riverLiftIrrigation) {
        this.riverLiftIrrigation = riverLiftIrrigation;
    }

    public String getCanalIrrigation() {
        return canalIrrigation;
    }

    public void setCanalIrrigation(String canalIrrigation) {
        this.canalIrrigation = canalIrrigation;
    }

    public String getTankIrrigation() {
        return tankIrrigation;
    }

    public void setTankIrrigation(String tankIrrigation) {
        this.tankIrrigation = tankIrrigation;
    }

    public String getWellIrrigation() {
        return wellIrrigation;
    }

    public void setWellIrrigation(String wellIrrigation) {
        this.wellIrrigation = wellIrrigation;
    }

    public String getShallowBorewell() {
        return shallowBorewell;
    }

    public void setShallowBorewell(String shallowBorewell) {
        this.shallowBorewell = shallowBorewell;
    }

    public String getMediumBoreWell() {
        return mediumBoreWell;
    }

    public void setMediumBoreWell(String mediumBoreWell) {
        this.mediumBoreWell = mediumBoreWell;
    }

    public String getDeepBoreBoreWell() {
        return deepBoreBoreWell;
    }

    public void setDeepBoreBoreWell(String deepBoreBoreWell) {
        this.deepBoreBoreWell = deepBoreBoreWell;
    }

    public String getDieselKerosenePumps() {
        return dieselKerosenePumps;
    }

    public void setDieselKerosenePumps(String dieselKerosenePumps) {
        this.dieselKerosenePumps = dieselKerosenePumps;
    }

    public String getElectricPumps() {
        return electricPumps;
    }

    public void setElectricPumps(String electricPumps) {
        this.electricPumps = electricPumps;
    }

    public String getSolarPumps() {
        return solarPumps;
    }

    public void setSolarPumps(String solarPumps) {
        this.solarPumps = solarPumps;
    }

    public String getFloodIrrigation() {
        return floodIrrigation;
    }

    public void setFloodIrrigation(String floodIrrigation) {
        this.floodIrrigation = floodIrrigation;
    }

    public String getDripIrrigation() {
        return dripIrrigation;
    }

    public void setDripIrrigation(String dripIrrigation) {
        this.dripIrrigation = dripIrrigation;
    }

    public String getSprinklerIrrigation() {
        return sprinklerIrrigation;
    }

    public void setSprinklerIrrigation(String sprinklerIrrigation) {
        this.sprinklerIrrigation = sprinklerIrrigation;
    }

    public String getRainGunIrrigation() {
        return rainGunIrrigation;
    }

    public void setRainGunIrrigation(String rainGunIrrigation) {
        this.rainGunIrrigation = rainGunIrrigation;
    }

    public String getMajorCropsRabi() {
        return majorCropsRabi;
    }

    public void setMajorCropsRabi(String majorCropsRabi) {
        this.majorCropsRabi = majorCropsRabi;
    }

    public String getMajorCropsSummer() {
        return majorCropsSummer;
    }

    public void setMajorCropsSummer(String majorCropsSummer) {
        this.majorCropsSummer = majorCropsSummer;
    }

    public String getMajorCropsKharif() {
        return majorCropsKharif;
    }

    public void setMajorCropsKharif(String majorCropsKharif) {
        this.majorCropsKharif = majorCropsKharif;
    }

    public String getRankValuableCrops() {
        return rankValuableCrops;
    }

    public void setRankValuableCrops(String rankValuableCrops) {
        this.rankValuableCrops = rankValuableCrops;
    }

    public String getRankLargeVolumeCrops() {
        return rankLargeVolumeCrops;
    }

    public void setRankLargeVolumeCrops(String rankLargeVolumeCrops) {
        this.rankLargeVolumeCrops = rankLargeVolumeCrops;
    }

    public String getTractor() {
        return tractor;
    }

    public void setTractor(String tractor) {
        this.tractor = tractor;
    }

    public String getIfHireCostTractor() {
        return ifHireCostTractor;
    }

    public void setIfHireCostTractor(String ifHireCostTractor) {
        this.ifHireCostTractor = ifHireCostTractor;
    }

    public String getTiller() {
        return tiller;
    }

    public void setTiller(String tiller) {
        this.tiller = tiller;
    }

    public String getIfHireCostTiller() {
        return ifHireCostTiller;
    }

    public void setIfHireCostTiller(String ifHireCostTiller) {
        this.ifHireCostTiller = ifHireCostTiller;
    }

    public String getIrrigation() {
        return irrigation;
    }

    public void setIrrigation(String irrigation) {
        this.irrigation = irrigation;
    }

    public String getIfHireCostIrrigation() {
        return ifHireCostIrrigation;
    }

    public void setIfHireCostIrrigation(String ifHireCostIrrigation) {
        this.ifHireCostIrrigation = ifHireCostIrrigation;
    }

    public String getHarvester() {
        return harvester;
    }

    public void setHarvester(String harvester) {
        this.harvester = harvester;
    }

    public String getIfHireCostHarvester() {
        return ifHireCostHarvester;
    }

    public void setIfHireCostHarvester(String ifHireCostHarvester) {
        this.ifHireCostHarvester = ifHireCostHarvester;
    }

    public String getTrasher() {
        return trasher;
    }

    public void setTrasher(String trasher) {
        this.trasher = trasher;
    }

    public String getIfHireCostTrasher() {
        return ifHireCostTrasher;
    }

    public void setIfHireCostTrasher(String ifHireCostTrasher) {
        this.ifHireCostTrasher = ifHireCostTrasher;
    }

    public String getDryer() {
        return dryer;
    }

    public void setDryer(String dryer) {
        this.dryer = dryer;
    }

    public String getIfHireCostDryer() {
        return ifHireCostDryer;
    }

    public void setIfHireCostDryer(String ifHireCostDryer) {
        this.ifHireCostDryer = ifHireCostDryer;
    }

    public String getColdStorageMonthlyRental() {
        return coldStorageMonthlyRental;
    }

    public void setColdStorageMonthlyRental(String coldStorageMonthlyRental) {
        this.coldStorageMonthlyRental = coldStorageMonthlyRental;
    }

    public String getMandalLevelLicensedShop() {
        return mandalLevelLicensedShop;
    }

    public void setMandalLevelLicensedShop(String mandalLevelLicensedShop) {
        this.mandalLevelLicensedShop = mandalLevelLicensedShop;
    }

    public String getDistrictLevelLicensedShop() {
        return districtLevelLicensedShop;
    }

    public void setDistrictLevelLicensedShop(String districtLevelLicensedShop) {
        this.districtLevelLicensedShop = districtLevelLicensedShop;
    }

    public String getLocalLicensedShop() {
        return localLicensedShop;
    }

    public void setLocalLicensedShop(String localLicensedShop) {
        this.localLicensedShop = localLicensedShop;
    }

    public String getLocalUnLicensedShop() {
        return localUnLicensedShop;
    }

    public void setLocalUnLicensedShop(String localUnLicensedShop) {
        this.localUnLicensedShop = localUnLicensedShop;
    }

    public String getResearchUniversity() {
        return researchUniversity;
    }

    public void setResearchUniversity(String researchUniversity) {
        this.researchUniversity = researchUniversity;
    }

    public String getOthersSource() {
        return othersSource;
    }

    public void setOthersSource(String othersSource) {
        this.othersSource = othersSource;
    }

    public String getIsLaborEasilyAccessible() {
        return isLaborEasilyAccessible;
    }

    public void setIsLaborEasilyAccessible(String isLaborEasilyAccessible) {
        this.isLaborEasilyAccessible = isLaborEasilyAccessible;
    }

    public String getIsLaborEasilyAccessibleSpecify() {
        return isLaborEasilyAccessibleSpecify;
    }

    public void setIsLaborEasilyAccessibleSpecify(String isLaborEasilyAccessibleSpecify) {
        this.isLaborEasilyAccessibleSpecify = isLaborEasilyAccessibleSpecify;
    }

    public String getWageMalePerDay() {
        return wageMalePerDay;
    }

    public void setWageMalePerDay(String wageMalePerDay) {
        this.wageMalePerDay = wageMalePerDay;
    }

    public String getWageFeMalePerDay() {
        return wageFeMalePerDay;
    }

    public void setWageFeMalePerDay(String wageFeMalePerDay) {
        this.wageFeMalePerDay = wageFeMalePerDay;
    }

    public String getInformationAgriBestPractices() {
        return informationAgriBestPractices;
    }

    public void setInformationAgriBestPractices(String informationAgriBestPractices) {
        this.informationAgriBestPractices = informationAgriBestPractices;
    }

    public String getWhoProvidesGuidance() {
        return whoProvidesGuidance;
    }

    public void setWhoProvidesGuidance(String whoProvidesGuidance) {
        this.whoProvidesGuidance = whoProvidesGuidance;
    }

    public String getTrainingsParticipated() {
        return trainingsParticipated;
    }

    public void setTrainingsParticipated(String trainingsParticipated) {
        this.trainingsParticipated = trainingsParticipated;
    }

    public String getUseWeatherInformation() {
        return useWeatherInformation;
    }

    public void setUseWeatherInformation(String useWeatherInformation) {
        this.useWeatherInformation = useWeatherInformation;
    }

    public String getIfYesWhatSource() {
        return ifYesWhatSource;
    }

    public void setIfYesWhatSource(String ifYesWhatSource) {
        this.ifYesWhatSource = ifYesWhatSource;
    }

    public String getInvestmentForCropping() {
        return investmentForCropping;
    }

    public void setInvestmentForCropping(String investmentForCropping) {
        this.investmentForCropping = investmentForCropping;
    }

    public String getOwnKisanCreditCard() {
        return ownKisanCreditCard;
    }

    public void setOwnKisanCreditCard(String ownKisanCreditCard) {
        this.ownKisanCreditCard = ownKisanCreditCard;
    }

    public String getLocalMoneyLender() {
        return localMoneyLender;
    }

    public void setLocalMoneyLender(String localMoneyLender) {
        this.localMoneyLender = localMoneyLender;
    }

    public String getsHGGroups() {
        return sHGGroups;
    }

    public void setsHGGroups(String sHGGroups) {
        this.sHGGroups = sHGGroups;
    }

    public String getInputShops() {
        return inputShops;
    }

    public void setInputShops(String inputShops) {
        this.inputShops = inputShops;
    }

    public String getTraderAggregator() {
        return traderAggregator;
    }

    public void setTraderAggregator(String traderAggregator) {
        this.traderAggregator = traderAggregator;
    }

    public String getmFI() {
        return mFI;
    }

    public void setmFI(String mFI) {
        this.mFI = mFI;
    }

    public String getkCC() {
        return kCC;
    }

    public void setkCC(String kCC) {
        this.kCC = kCC;
    }

    public String getLocalLandCooperative() {
        return localLandCooperative;
    }

    public void setLocalLandCooperative(String localLandCooperative) {
        this.localLandCooperative = localLandCooperative;
    }

    public String getNormalBank() {
        return normalBank;
    }

    public void setNormalBank(String normalBank) {
        this.normalBank = normalBank;
    }

    public String getOthersSourceFinance() {
        return othersSourceFinance;
    }

    public void setOthersSourceFinance(String othersSourceFinance) {
        this.othersSourceFinance = othersSourceFinance;
    }

    public String getLoanRepayment() {
        return loanRepayment;
    }

    public void setLoanRepayment(String loanRepayment) {
        this.loanRepayment = loanRepayment;
    }

    public String getDailyMarketNos() {
        return dailyMarketNos;
    }

    public void setDailyMarketNos(String dailyMarketNos) {
        this.dailyMarketNos = dailyMarketNos;
    }

    public String getDailyMarketKms() {
        return dailyMarketKms;
    }

    public void setDailyMarketKms(String dailyMarketKms) {
        this.dailyMarketKms = dailyMarketKms;
    }

    public String getWeeklyHaatNos() {
        return weeklyHaatNos;
    }

    public void setWeeklyHaatNos(String weeklyHaatNos) {
        this.weeklyHaatNos = weeklyHaatNos;
    }

    public String getWeeklyHaatKms() {
        return weeklyHaatKms;
    }

    public void setWeeklyHaatKms(String weeklyHaatKms) {
        this.weeklyHaatKms = weeklyHaatKms;
    }

    public String getBlockMandiNos() {
        return blockMandiNos;
    }

    public void setBlockMandiNos(String blockMandiNos) {
        this.blockMandiNos = blockMandiNos;
    }

    public String getBlockMandiKms() {
        return blockMandiKms;
    }

    public void setBlockMandiKms(String blockMandiKms) {
        this.blockMandiKms = blockMandiKms;
    }

    public String getDistrictAPMCNos() {
        return districtAPMCNos;
    }

    public void setDistrictAPMCNos(String districtAPMCNos) {
        this.districtAPMCNos = districtAPMCNos;
    }

    public String getDistrictAPMCKms() {
        return districtAPMCKms;
    }

    public void setDistrictAPMCKms(String districtAPMCKms) {
        this.districtAPMCKms = districtAPMCKms;
    }

    public String getSpecialMarketsNos() {
        return specialMarketsNos;
    }

    public void setSpecialMarketsNos(String specialMarketsNos) {
        this.specialMarketsNos = specialMarketsNos;
    }

    public String getSpecialMarketsKms() {
        return specialMarketsKms;
    }

    public void setSpecialMarketsKms(String specialMarketsKms) {
        this.specialMarketsKms = specialMarketsKms;
    }

    public String getSourceInformationCurrent() {
        return sourceInformationCurrent;
    }

    public void setSourceInformationCurrent(String sourceInformationCurrent) {
        this.sourceInformationCurrent = sourceInformationCurrent;
    }

    public String getTimeOfSale() {
        return timeOfSale;
    }

    public void setTimeOfSale(String timeOfSale) {
        this.timeOfSale = timeOfSale;
    }

    public String getStorageSystems() {
        return storageSystems;
    }

    public void setStorageSystems(String storageSystems) {
        this.storageSystems = storageSystems;
    }

    public String getStorageItems() {
        return storageItems;
    }

    public void setStorageItems(String storageItems) {
        this.storageItems = storageItems;
    }

    public String getSellCropsFruitsLocalTrader() {
        return sellCropsFruitsLocalTrader;
    }

    public void setSellCropsFruitsLocalTrader(String sellCropsFruitsLocalTrader) {
        this.sellCropsFruitsLocalTrader = sellCropsFruitsLocalTrader;
    }

    public String getSellCropsFruitsMandi() {
        return sellCropsFruitsMandi;
    }

    public void setSellCropsFruitsMandi(String sellCropsFruitsMandi) {
        this.sellCropsFruitsMandi = sellCropsFruitsMandi;
    }

    public String getSellCropsFruitsOthers() {
        return sellCropsFruitsOthers;
    }

    public void setSellCropsFruitsOthers(String sellCropsFruitsOthers) {
        this.sellCropsFruitsOthers = sellCropsFruitsOthers;
    }

    public String getSellCropsFruitsPrice() {
        return sellCropsFruitsPrice;
    }

    public void setSellCropsFruitsPrice(String sellCropsFruitsPrice) {
        this.sellCropsFruitsPrice = sellCropsFruitsPrice;
    }

    public String getSellCropsFruitsPayment() {
        return sellCropsFruitsPayment;
    }

    public void setSellCropsFruitsPayment(String sellCropsFruitsPayment) {
        this.sellCropsFruitsPayment = sellCropsFruitsPayment;
    }

    public String getSellCropsCerealsLocalTrader() {
        return sellCropsCerealsLocalTrader;
    }

    public void setSellCropsCerealsLocalTrader(String sellCropsCerealsLocalTrader) {
        this.sellCropsCerealsLocalTrader = sellCropsCerealsLocalTrader;
    }

    public String getSellCropsCerealsMandi() {
        return sellCropsCerealsMandi;
    }

    public void setSellCropsCerealsMandi(String sellCropsCerealsMandi) {
        this.sellCropsCerealsMandi = sellCropsCerealsMandi;
    }

    public String getSellCropsCerealsPACS() {
        return sellCropsCerealsPACS;
    }

    public void setSellCropsCerealsPACS(String sellCropsCerealsPACS) {
        this.sellCropsCerealsPACS = sellCropsCerealsPACS;
    }

    public String getSellCropsCerealsFPCPG() {
        return sellCropsCerealsFPCPG;
    }

    public void setSellCropsCerealsFPCPG(String sellCropsCerealsFPCPG) {
        this.sellCropsCerealsFPCPG = sellCropsCerealsFPCPG;
    }

    public String getSellCropsCerealscompaniesITC() {
        return sellCropsCerealscompaniesITC;
    }

    public void setSellCropsCerealscompaniesITC(String sellCropsCerealscompaniesITC) {
        this.sellCropsCerealscompaniesITC = sellCropsCerealscompaniesITC;
    }

    public String getSellCropsCerealsOthers() {
        return sellCropsCerealsOthers;
    }

    public void setSellCropsCerealsOthers(String sellCropsCerealsOthers) {
        this.sellCropsCerealsOthers = sellCropsCerealsOthers;
    }

    public String getSellCropsCerealsPrice() {
        return sellCropsCerealsPrice;
    }

    public void setSellCropsCerealsPrice(String sellCropsCerealsPrice) {
        this.sellCropsCerealsPrice = sellCropsCerealsPrice;
    }

    public String getSellCropsCerealsPayment() {
        return sellCropsCerealsPayment;
    }

    public void setSellCropsCerealsPayment(String sellCropsCerealsPayment) {
        this.sellCropsCerealsPayment = sellCropsCerealsPayment;
    }

    public String getSellCropsVegetablesLocalTrader() {
        return sellCropsVegetablesLocalTrader;
    }

    public void setSellCropsVegetablesLocalTrader(String sellCropsVegetablesLocalTrader) {
        this.sellCropsVegetablesLocalTrader = sellCropsVegetablesLocalTrader;
    }

    public String getSellCropsVegetablesTrader() {
        return sellCropsVegetablesTrader;
    }

    public void setSellCropsVegetablesTrader(String sellCropsVegetablesTrader) {
        this.sellCropsVegetablesTrader = sellCropsVegetablesTrader;
    }

    public String getSellCropsVegetablesProcessingCompanies() {
        return sellCropsVegetablesProcessingCompanies;
    }

    public void setSellCropsVegetablesProcessingCompanies(String sellCropsVegetablesProcessingCompanies) {
        this.sellCropsVegetablesProcessingCompanies = sellCropsVegetablesProcessingCompanies;
    }

    public String getSellCropsVegetablesOthers() {
        return sellCropsVegetablesOthers;
    }

    public void setSellCropsVegetablesOthers(String sellCropsVegetablesOthers) {
        this.sellCropsVegetablesOthers = sellCropsVegetablesOthers;
    }

    public String getSellCropsVegetablesPrice() {
        return sellCropsVegetablesPrice;
    }

    public void setSellCropsVegetablesPrice(String sellCropsVegetablesPrice) {
        this.sellCropsVegetablesPrice = sellCropsVegetablesPrice;
    }

    public String getSellCropsVegetablesPayment() {
        return sellCropsVegetablesPayment;
    }

    public void setSellCropsVegetablesPayment(String sellCropsVegetablesPayment) {
        this.sellCropsVegetablesPayment = sellCropsVegetablesPayment;
    }


    public String getIncomeFromCrops() {
        return incomeFromCrops;
    }

    public void setIncomeFromCrops(String incomeFromCrops) {
        this.incomeFromCrops = incomeFromCrops;
    }

    public String getIncomeFromLivestock() {
        return incomeFromLivestock;
    }

    public void setIncomeFromLivestock(String incomeFromLivestock) {
        this.incomeFromLivestock = incomeFromLivestock;
    }

    public String getIncomeFromAgriLabour() {
        return incomeFromAgriLabour;
    }

    public void setIncomeFromAgriLabour(String incomeFromAgriLabour) {
        this.incomeFromAgriLabour = incomeFromAgriLabour;
    }

    public String getIncomeFromMigrationLabour() {
        return incomeFromMigrationLabour;
    }

    public void setIncomeFromMigrationLabour(String incomeFromMigrationLabour) {
        this.incomeFromMigrationLabour = incomeFromMigrationLabour;
    }

    public String getIncomeFromService() {
        return incomeFromService;
    }

    public void setIncomeFromService(String incomeFromService) {
        this.incomeFromService = incomeFromService;
    }

    public String getIncomeFromShopTrade() {
        return incomeFromShopTrade;
    }

    public void setIncomeFromShopTrade(String incomeFromShopTrade) {
        this.incomeFromShopTrade = incomeFromShopTrade;
    }

    public String getThreeMealsOfFood() {
        return threeMealsOfFood;
    }

    public void setThreeMealsOfFood(String threeMealsOfFood) {
        this.threeMealsOfFood = threeMealsOfFood;
    }

    public String getLandPreparation() {
        return landPreparation;
    }

    public void setLandPreparation(String landPreparation) {
        this.landPreparation = landPreparation;
    }

    public String getSeeds() {
        return seeds;
    }

    public void setSeeds(String seeds) {
        this.seeds = seeds;
    }

    public String getFertilizer() {
        return fertilizer;
    }

    public void setFertilizer(String fertilizer) {
        this.fertilizer = fertilizer;
    }

    public String getPesticides() {
        return pesticides;
    }

    public void setPesticides(String pesticides) {
        this.pesticides = pesticides;
    }

    public String getIrrigationExpan() {
        return irrigationExpan;
    }

    public void setIrrigationExpan(String irrigationExpan) {
        this.irrigationExpan = irrigationExpan;
    }

    public String getWeeding() {
        return weeding;
    }

    public void setWeeding(String weeding) {
        this.weeding = weeding;
    }

    public String getHarvesting() {
        return harvesting;
    }

    public void setHarvesting(String harvesting) {
        this.harvesting = harvesting;
    }

    public String getCleaningSortingPacking() {
        return cleaningSortingPacking;
    }

    public void setCleaningSortingPacking(String cleaningSortingPacking) {
        this.cleaningSortingPacking = cleaningSortingPacking;
    }

    public String getTransportation() {
        return transportation;
    }

    public void setTransportation(String transportation) {
        this.transportation = transportation;
    }

    public String getStorage() {
        return storage;
    }

    public void setStorage(String storage) {
        this.storage = storage;
    }

    public String getMilkingCow() {
        return milkingCow;
    }

    public void setMilkingCow(String milkingCow) {
        this.milkingCow = milkingCow;
    }

    public String getSheepGoats() {
        return sheepGoats;
    }

    public void setSheepGoats(String sheepGoats) {
        this.sheepGoats = sheepGoats;
    }

    public String getBackyardPoultry() {
        return backyardPoultry;
    }

    public void setBackyardPoultry(String backyardPoultry) {
        this.backyardPoultry = backyardPoultry;
    }

    public String getAquaculturePond() {
        return aquaculturePond;
    }

    public void setAquaculturePond(String aquaculturePond) {
        this.aquaculturePond = aquaculturePond;
    }

    public String getIfYesCountAquaculturePond() {
        return ifYesCountAquaculturePond;
    }

    public void setIfYesCountAquaculturePond(String ifYesCountAquaculturePond) {
        this.ifYesCountAquaculturePond = ifYesCountAquaculturePond;
    }

    public String getTractors() {
        return tractors;
    }

    public void setTractors(String tractors) {
        this.tractors = tractors;
    }

    public String getIfYesCountTractors() {
        return ifYesCountTractors;
    }

    public void setIfYesCountTractors(String ifYesCountTractors) {
        this.ifYesCountTractors = ifYesCountTractors;
    }

    public String getTillers() {
        return tillers;
    }

    public void setTillers(String tillers) {
        this.tillers = tillers;
    }

    public String getIfYesCountTillers() {
        return ifYesCountTillers;
    }

    public void setIfYesCountTillers(String ifYesCountTillers) {
        this.ifYesCountTillers = ifYesCountTillers;
    }

    public String getHarvesters() {
        return harvesters;
    }

    public void setHarvesters(String harvesters) {
        this.harvesters = harvesters;
    }

    public String getIfYesCountHarvesters() {
        return ifYesCountHarvesters;
    }

    public void setIfYesCountHarvesters(String ifYesCountHarvesters) {
        this.ifYesCountHarvesters = ifYesCountHarvesters;
    }

    public String getUploadAudioUrl1() {
        return uploadAudioUrl1;
    }

    public void setUploadAudioUrl1(String uploadAudioUrl1) {
        this.uploadAudioUrl1 = uploadAudioUrl1;
    }

    public String getUploadAudioUrl2() {
        return uploadAudioUrl2;
    }

    public void setUploadAudioUrl2(String uploadAudioUrl2) {
        this.uploadAudioUrl2 = uploadAudioUrl2;
    }

    public String getUploadAudioUrl3() {
        return uploadAudioUrl3;
    }

    public void setUploadAudioUrl3(String uploadAudioUrl3) {
        this.uploadAudioUrl3 = uploadAudioUrl3;
    }

    public String getUploadAudioUrl4() {
        return uploadAudioUrl4;
    }

    public void setUploadAudioUrl4(String uploadAudioUrl4) {
        this.uploadAudioUrl4 = uploadAudioUrl4;
    }

    public String getUploadAudioUrl5() {
        return uploadAudioUrl5;
    }

    public void setUploadAudioUrl5(String uploadAudioUrl5) {
        this.uploadAudioUrl5 = uploadAudioUrl5;
    }

    public String getUploadAudioUrl6() {
        return uploadAudioUrl6;
    }

    public void setUploadAudioUrl6(String uploadAudioUrl6) {
        this.uploadAudioUrl6 = uploadAudioUrl6;
    }

    public String getUploadAudioUrl7() {
        return uploadAudioUrl7;
    }

    public void setUploadAudioUrl7(String uploadAudioUrl7) {
        this.uploadAudioUrl7 = uploadAudioUrl7;
    }

    public String getUploadAudioUrl8() {
        return uploadAudioUrl8;
    }

    public void setUploadAudioUrl8(String uploadAudioUrl8) {
        this.uploadAudioUrl8 = uploadAudioUrl8;
    }

    public String getUploadAudioUrl9() {
        return uploadAudioUrl9;
    }

    public void setUploadAudioUrl9(String uploadAudioUrl9) {
        this.uploadAudioUrl9 = uploadAudioUrl9;
    }

    public String getUploadAudioUrl10() {
        return uploadAudioUrl10;
    }

    public void setUploadAudioUrl10(String uploadAudioUrl10) {
        this.uploadAudioUrl10 = uploadAudioUrl10;
    }

    public ArrayList<Base> getBases1() {
        return bases1;
    }

    public void setBases1(ArrayList<Base> bases1) {
        this.bases1 = bases1;
    }

    public ArrayList<Base> getBases2() {
        return bases2;
    }

    public void setBases2(ArrayList<Base> bases2) {
        this.bases2 = bases2;
    }

    public ArrayList<Base> getBases3() {
        return bases3;
    }

    public void setBases3(ArrayList<Base> bases3) {
        this.bases3 = bases3;
    }

    public ArrayList<Base> getBases4() {
        return bases4;
    }

    public void setBases4(ArrayList<Base> bases4) {
        this.bases4 = bases4;
    }

    public ArrayList<Base> getBases5() {
        return bases5;
    }

    public void setBases5(ArrayList<Base> bases5) {
        this.bases5 = bases5;
    }

    public ArrayList<Base> getBases6() {
        return bases6;
    }

    public void setBases6(ArrayList<Base> bases6) {
        this.bases6 = bases6;
    }

    public ArrayList<Base> getBases7() {
        return bases7;
    }

    public void setBases7(ArrayList<Base> bases7) {
        this.bases7 = bases7;
    }

    public ArrayList<Base> getBases8() {
        return bases8;
    }

    public void setBases8(ArrayList<Base> bases8) {
        this.bases8 = bases8;
    }

    public ArrayList<Base> getBases9() {
        return bases9;
    }

    public void setBases9(ArrayList<Base> bases9) {
        this.bases9 = bases9;
    }

    public ArrayList<Base> getBases10() {
        return bases10;
    }

    public void setBases10(ArrayList<Base> bases10) {
        this.bases10 = bases10;
    }

    public ArrayList<Member> getMemberArrayList() {
        return memberArrayList;
    }

    public void setMemberArrayList(ArrayList<Member> memberArrayList) {
        this.memberArrayList = memberArrayList;
    }

    public ArrayList<Village> getVillageArrayList() {
        return villageArrayList;
    }

    public void setVillageArrayList(ArrayList<Village> villageArrayList) {
        this.villageArrayList = villageArrayList;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }
}
