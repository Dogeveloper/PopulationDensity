package me.ryanhamshire.PopulationDensity;

public class ScanOpenRegionTask implements Runnable 
{

	public void run() 
	{
		//start a scan on the currently open region
		PopulationDensity.instance.scanRegion(PopulationDensity.instance.dataStore.getOpenRegion(), true);		
	}	
}
