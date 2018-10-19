package com.vp.plugin.sample.archiMatePlugin;

import java.awt.Color;
import java.awt.Point;

import com.vp.plugin.ApplicationManager;
import com.vp.plugin.DiagramManager;
import com.vp.plugin.action.VPAction;
import com.vp.plugin.action.VPActionController;
import com.vp.plugin.diagram.IArchiMateDiagramUIModel;
import com.vp.plugin.diagram.ICaptionUIModel;
import com.vp.plugin.diagram.IDiagramTypeConstants;
import com.vp.plugin.diagram.shape.IArchiMateDeviceUIModel;
import com.vp.plugin.diagram.shape.IArchiMateInfrastructureServiceUIModel;
import com.vp.plugin.diagram.shape.IArchiMateLocationUIModel;
import com.vp.plugin.diagram.shape.IArchiMateNetworkBoxUIModel;
import com.vp.plugin.diagram.shape.IArchiMateNodeUIModel;
import com.vp.plugin.diagram.shape.IArchiMateSystemSoftwareUIModel;
import com.vp.plugin.model.IArchiMateAssociation;
import com.vp.plugin.model.IArchiMateDevice;
import com.vp.plugin.model.IArchiMateInfrastructureService;
import com.vp.plugin.model.IArchiMateLocation;
import com.vp.plugin.model.IArchiMateNetworkBox;
import com.vp.plugin.model.IArchiMateNode;
import com.vp.plugin.model.IArchiMateRealization;
import com.vp.plugin.model.IArchiMateSystemSoftware;
import com.vp.plugin.model.factory.IModelElementFactory;

public class ArchiMateAction implements VPActionController {

	@Override
	public void performAction(VPAction arg0) {
		//Create blank diagram
		DiagramManager diagramManager = ApplicationManager.instance().getDiagramManager();
		IArchiMateDiagramUIModel archiMate = (IArchiMateDiagramUIModel) diagramManager.createDiagram(IDiagramTypeConstants.DIAGRAM_TYPE_ARCHI_MATE_DIAGRAM);
		archiMate.setName("Sample ArchiMate Diagram");

		
		//Create Locations
		IArchiMateLocation archiSuranceOffice = IModelElementFactory.instance().createArchiMateLocation();
		archiSuranceOffice.setName("AchiSurance Office");
		//Create shape for location on diagram
		IArchiMateLocationUIModel shapeArchiSuranceOffice = (IArchiMateLocationUIModel) diagramManager.createDiagramElement(archiMate, archiSuranceOffice);
		shapeArchiSuranceOffice.setBounds(50, 100, 400, 400);
		shapeArchiSuranceOffice.resetCaption();
		
		IArchiMateLocation intermediaryOfficce = IModelElementFactory.instance().createArchiMateLocation();
		intermediaryOfficce.setName("intermediary Office");
		IArchiMateLocationUIModel shapeIntermediaryOffice = (IArchiMateLocationUIModel) diagramManager.createDiagramElement(archiMate, intermediaryOfficce);
		shapeIntermediaryOffice.setBounds(600, 100, 350, 400);
		shapeIntermediaryOffice.resetCaption();
		

		//Create Nodes
		IArchiMateNode mainframe = IModelElementFactory.instance().createArchiMateNode();
		mainframe.setName("Mainframe");
		// Add Mainframe to become child of ArchiSurance Office
		archiSuranceOffice.addChild(mainframe);
		//Create shape on diagram
		IArchiMateNodeUIModel shapeMainframe = (IArchiMateNodeUIModel) diagramManager.createDiagramElement(archiMate, mainframe);
		shapeMainframe.setBounds(75, 150, 150, 250);
		//show the node as a symbol instead of a box
		shapeMainframe.setShowOption(IArchiMateNodeUIModel.SHOW_OPTION_AS_SYMBOL);
		shapeArchiSuranceOffice.addChild(shapeMainframe);
		shapeMainframe.resetCaption();

		IArchiMateNode unixServerFarm = IModelElementFactory.instance().createArchiMateNode();
		unixServerFarm.setName("Unix Server Farm");
		archiSuranceOffice.addChild(unixServerFarm);
		IArchiMateNodeUIModel shapeUnixServerFarm = (IArchiMateNodeUIModel) diagramManager.createDiagramElement(archiMate, unixServerFarm);
		shapeUnixServerFarm.setBounds(250, 350, 175, 125);
		shapeUnixServerFarm.setShowOption(IArchiMateNodeUIModel.SHOW_OPTION_AS_SYMBOL);
		shapeArchiSuranceOffice.addChild(shapeUnixServerFarm);
		shapeUnixServerFarm.resetCaption();
		
		IArchiMateNode firewall = IModelElementFactory.instance().createArchiMateNode();
		firewall.setName("Firewall");
		archiSuranceOffice.addChild(firewall);
		IArchiMateNodeUIModel shapeFirewallMaster = (IArchiMateNodeUIModel) diagramManager.createDiagramElement(archiMate, firewall);
		shapeFirewallMaster.setBounds(345, 280, 80, 40);
		shapeFirewallMaster.getFillColor().setColor1(new Color(255, 128, 128));
		shapeFirewallMaster.setShowOption(IArchiMateNodeUIModel.SHOW_OPTION_AS_SYMBOL);
		shapeArchiSuranceOffice.addChild(shapeFirewallMaster);
		shapeFirewallMaster.resetCaption();
		IArchiMateNodeUIModel shapeFireWall = (IArchiMateNodeUIModel) diagramManager.createDiagramElement(archiMate, firewall);
		shapeFireWall.setBounds(625, 280, 80, 40);
		shapeFireWall.getFillColor().setColor1(new Color(255, 128, 128));
		shapeFireWall.setShowOption(IArchiMateNodeUIModel.SHOW_OPTION_AS_SYMBOL);
		shapeIntermediaryOffice.addChild(shapeFireWall);
		shapeFireWall.resetCaption();
		shapeFirewallMaster.toBeMasterView();
		
		
		//Create System software
		IArchiMateSystemSoftware cics = mainframe.createArchiMateSystemSoftware();
		cics.setName("CICS");
		//Create shape on diagram
		IArchiMateSystemSoftwareUIModel shapeCics = (IArchiMateSystemSoftwareUIModel) diagramManager.createDiagramElement(archiMate, cics);
		shapeCics.setBounds(100, 200, 90, 40);
		//Show the shape as a box instead of a symbol
		shapeCics.setShowOption(IArchiMateSystemSoftwareUIModel.SHOW_OPTION_AS_BOX);
		shapeMainframe.addChild(shapeCics);
		shapeCics.resetCaption();

		IArchiMateSystemSoftware dbms = mainframe.createArchiMateSystemSoftware();
		dbms.setName("DBMS");
		IArchiMateSystemSoftwareUIModel shapeDbms = (IArchiMateSystemSoftwareUIModel) diagramManager.createDiagramElement(archiMate, dbms);
		shapeDbms.setBounds(100, 260, 90, 40);
		shapeDbms.setShowOption(IArchiMateSystemSoftwareUIModel.SHOW_OPTION_AS_BOX);
		shapeMainframe.addChild(shapeDbms);
		shapeDbms.resetCaption();

		IArchiMateSystemSoftware messageQueing = mainframe.createArchiMateSystemSoftware();
		messageQueing.setName("Message Queing");
		IArchiMateSystemSoftwareUIModel shapeMessageQueing = (IArchiMateSystemSoftwareUIModel) diagramManager.createDiagramElement(archiMate, messageQueing);
		shapeMessageQueing.setBounds(100, 320, 90, 40);
		shapeMessageQueing.setShowOption(IArchiMateSystemSoftwareUIModel.SHOW_OPTION_AS_BOX);
		shapeMainframe.addChild(shapeMessageQueing);
		shapeMessageQueing.resetCaption();
		

		//Create devices
		IArchiMateDevice unixServer1 = unixServerFarm.createArchiMateDevice();
		unixServer1.setName("UNIX Server");
		unixServerFarm.addArchiMateDevice(unixServer1);
		//Create shape on diagram
		IArchiMateDeviceUIModel shapeUnixServer1 = (IArchiMateDeviceUIModel) diagramManager.createDiagramElement(archiMate, unixServer1);
		shapeUnixServer1.setBounds(260, 400, 60, 50);
		shapeUnixServer1.setShowOption(IArchiMateDeviceUIModel.SHOW_OPTION_AS_SYMBOL);
		shapeUnixServerFarm.addChild(shapeUnixServer1);
		shapeUnixServer1.resetCaption();
		//Set the caption to be shown below the shape
		shapeUnixServer1.getCaptionUIModel().setSide(ICaptionUIModel.SIDE_SOUTH);

		IArchiMateDevice unixServer2 = (IArchiMateDevice) unixServer1.duplicate();
		IArchiMateDeviceUIModel shapeUnixServer2 = (IArchiMateDeviceUIModel) diagramManager.createDiagramElement(archiMate, unixServer2);
		unixServerFarm.addArchiMateDevice(unixServer2);
		shapeUnixServer2.setBounds(340, 400, 60, 50);
		shapeUnixServer2.setShowOption(IArchiMateDeviceUIModel.SHOW_OPTION_AS_SYMBOL);
		shapeUnixServerFarm.addChild(shapeUnixServer2);
		shapeUnixServer2.resetCaption();
		shapeUnixServer2.getCaptionUIModel().setSide(ICaptionUIModel.SIDE_SOUTH);

		IArchiMateDevice nasFileServer = IModelElementFactory.instance().createArchiMateDevice();
		nasFileServer.setName("NAS File Server");
		archiSuranceOffice.addChild(nasFileServer);
		IArchiMateDeviceUIModel shapeNasFileServer = (IArchiMateDeviceUIModel) diagramManager.createDiagramElement(archiMate, nasFileServer);
		shapeNasFileServer.setBounds(260, 185, 60, 50);
		shapeNasFileServer.setShowOption(IArchiMateDeviceUIModel.SHOW_OPTION_AS_SYMBOL);
		shapeArchiSuranceOffice.addChild(shapeNasFileServer);
		shapeNasFileServer.resetCaption();
		shapeNasFileServer.getCaptionUIModel().setSide(ICaptionUIModel.SIDE_SOUTH);
		
		//Create network box
		IArchiMateNetworkBox lan = IModelElementFactory.instance().createArchiMateNetworkBox();
		lan.setName("LAN");
		archiSuranceOffice.addChild(lan);
		//Create a shape on diagram
		IArchiMateNetworkBoxUIModel shapeLanMaster = (IArchiMateNetworkBoxUIModel) diagramManager.createDiagramElement(archiMate, lan);
		shapeLanMaster.setBounds(250, 280, 80, 40);
		shapeArchiSuranceOffice.addChild(shapeLanMaster);
		shapeLanMaster.resetCaption();
		//Create another shape on the diagram
		IArchiMateNetworkBoxUIModel shapeLanAbstract = (IArchiMateNetworkBoxUIModel) diagramManager.createDiagramElement(archiMate, lan);
		shapeLanAbstract.setBounds(745, 280, 80, 40);
		shapeIntermediaryOffice.addChild(shapeLanAbstract);
		shapeLanAbstract.resetCaption();
		//Set the first shape as Master view
		shapeLanMaster.toBeMasterView();

		IArchiMateNetworkBox bibit = IModelElementFactory.instance().createArchiMateNetworkBox();
		bibit.setName("BIBIT");
		IArchiMateNetworkBoxUIModel shapeBibit = (IArchiMateNetworkBoxUIModel) diagramManager.createDiagramElement(archiMate, bibit);
		shapeBibit.setBounds(485, 280, 80, 40);
		shapeBibit.resetCaption();

		IArchiMateDevice adminServer = IModelElementFactory.instance().createArchiMateDevice();
		adminServer.setName("Admin Server");
		intermediaryOfficce.addChild(adminServer);
		IArchiMateDeviceUIModel shapeAdminServer = (IArchiMateDeviceUIModel) diagramManager.createDiagramElement(archiMate, adminServer);
		shapeAdminServer.setBounds(860, 275, 60, 50);
		shapeAdminServer.setShowOption(IArchiMateDeviceUIModel.SHOW_OPTION_AS_SYMBOL);
		shapeIntermediaryOffice.addChild(shapeAdminServer);
		shapeAdminServer.resetCaption();
		shapeAdminServer.getCaptionUIModel().setSide(ICaptionUIModel.SIDE_SOUTH);

		
		//Create Service
		IArchiMateInfrastructureService databaseService = IModelElementFactory.instance().createArchiMateInfrastructureService();
		databaseService.setName("Database Service");
		//Create shape on diagram
		IArchiMateInfrastructureServiceUIModel shapeDatabaseService = (IArchiMateInfrastructureServiceUIModel) diagramManager.createDiagramElement(archiMate, databaseService);
		shapeDatabaseService.setBounds(75, 25, 150, 50);
		shapeDatabaseService.resetCaption();

		IArchiMateInfrastructureService fileService = IModelElementFactory.instance().createArchiMateInfrastructureService();
		fileService.setName("File Service");
		IArchiMateInfrastructureServiceUIModel shapeFileService = (IArchiMateInfrastructureServiceUIModel) diagramManager.createDiagramElement(archiMate, fileService);
		shapeFileService.setBounds(250, 25, 80, 50);
		shapeFileService.resetCaption();

		IArchiMateInfrastructureService networkService = IModelElementFactory.instance().createArchiMateInfrastructureService();
		networkService.setName("Network Service");
		IArchiMateInfrastructureServiceUIModel shapeNetworkService = (IArchiMateInfrastructureServiceUIModel) diagramManager.createDiagramElement(archiMate, networkService);
		shapeNetworkService.setBounds(345, 25, 100, 50);
		shapeNetworkService.resetCaption();

		
		// Create associations
		IArchiMateAssociation lanToDbms = IModelElementFactory.instance().createArchiMateAssociation();
		//the association starts from lan...
		lanToDbms.setFrom(lan);
		//...to dbms
		lanToDbms.setTo(dbms);
		//draw connector on diagram
		diagramManager.createConnector(archiMate, lanToDbms, shapeLanMaster,shapeDbms, new Point[] { new Point(250, 300),new Point(200, 300), new Point(200, 280),new Point(190, 280) });

		IArchiMateAssociation lanToNas = IModelElementFactory.instance().createArchiMateAssociation();
		lanToNas.setFrom(lan);
		lanToNas.setTo(nasFileServer);
		diagramManager.createConnector(archiMate, lanToNas, shapeLanMaster,shapeNasFileServer, new Point[] { new Point(290, 280),new Point(290, 235) });

		IArchiMateAssociation lanToUnixFerverFarm = IModelElementFactory.instance().createArchiMateAssociation();
		lanToUnixFerverFarm.setFrom(lan);
		lanToUnixFerverFarm.setTo(unixServerFarm);
		diagramManager.createConnector(archiMate, lanToUnixFerverFarm, shapeLanMaster, shapeUnixServerFarm, new Point[] {new Point(290, 320), new Point(290, 350) });

		IArchiMateAssociation lanToFirewall = IModelElementFactory.instance().createArchiMateAssociation();
		lanToFirewall.setFrom(lan);
		lanToFirewall.setTo(firewall);
		diagramManager.createConnector(archiMate, lanToFirewall, shapeLanMaster, shapeFirewallMaster, new Point[] {new Point(330, 300), new Point(345, 300) });
		diagramManager.createConnector(archiMate, lanToFirewall, shapeLanAbstract, shapeFireWall, new Point[] {new Point(745, 300), new Point(705, 300) });

		IArchiMateAssociation lanToAdminServer = IModelElementFactory.instance().createArchiMateAssociation();
		lanToAdminServer.setFrom(lan);
		lanToAdminServer.setTo(adminServer);
		diagramManager.createConnector(archiMate, lanToAdminServer, shapeLanAbstract, shapeAdminServer, new Point[] {new Point(825, 300), new Point(860, 300) });

		IArchiMateAssociation firewallToBibit = IModelElementFactory.instance().createArchiMateAssociation();
		firewallToBibit.setFrom(firewall);
		firewallToBibit.setTo(bibit);
		diagramManager.createConnector(archiMate, firewallToBibit, shapeFirewallMaster, shapeBibit, new Point[] {new Point(425, 300), new Point(485, 300) });
		diagramManager.createConnector(archiMate, firewallToBibit, shapeFireWall, shapeBibit, new Point[] {new Point(625, 300), new Point(565, 300) });

		
		// Create realizations
		IArchiMateRealization mainframeToDatabaseService = IModelElementFactory.instance().createArchiMateRealization();
		//The realization starts from mainframe...
		mainframeToDatabaseService.setFrom(mainframe);
		//... to databaseService
		mainframeToDatabaseService.setTo(databaseService);
		//Draw connection on diagram
		diagramManager.createConnector(archiMate, mainframeToDatabaseService,shapeMainframe, shapeDatabaseService, new Point[] {new Point(150, 150), new Point(150, 75) });

		IArchiMateRealization nasToFileService = IModelElementFactory.instance().createArchiMateRealization();
		nasToFileService.setFrom(nasFileServer);
		nasToFileService.setTo(fileService);
		diagramManager.createConnector(archiMate, nasToFileService, shapeNasFileServer, shapeFileService, new Point[] {new Point(290, 185), new Point(290, 75) });

		IArchiMateRealization firewallToNetworkService = IModelElementFactory.instance().createArchiMateRealization();
		firewallToNetworkService.setFrom(firewall);
		firewallToNetworkService.setTo(networkService);
		diagramManager.createConnector(archiMate, firewallToNetworkService, shapeFireWall, shapeNetworkService, new Point[] {new Point(385, 280), new Point(385, 75) });

		
		// Show up diagram
		diagramManager.openDiagram(archiMate);
	}

	@Override
	public void update(VPAction arg0) {
		// TODO Auto-generated method stub

	}

}
