package util;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.Reporter;

public class DatabaseConnectionUtils {


	Connection connection;
	/*
	 * Truncate table from IndigoData
	 * 
	 */
	//TODO Amiya modified 05/10/2018
	public void truncateT_Payment_Fail_Temp(Connection connectionWithIndigo) throws SQLException{
		try {
			Statement statement = connectionWithIndigo.createStatement();
			connectionWithIndigo.commit();
			statement.execute("truncate table INDIGO_DATA.T_PAYMENT_FAIL_TEMP");
			connectionWithIndigo.commit();
			Reporter.log("<B>INDIGO_DATA.T_PAYMENT_FAIL_TEMP</B> truncted");
			connectionWithIndigo.close();
			Reporter.log("Connection Closed... ");
		}catch (Exception e) {
			e.printStackTrace();
			connectionWithIndigo.close();
			Reporter.log("Connection Closed... ");
		}

	}
	//TODO Amiya modified 05/10/2018
	public String select_PA_TRANS_ID_fromPortalDB( Connection connectionWithQATPortal, String ProposalNumber,String chequeNumber) throws SQLException{
		String PA_TRANS_ID="";
		try {

			Statement stmt = connectionWithQATPortal.createStatement();
			ResultSet rs = stmt.executeQuery("select PA_TRANS_ID from portalindividual.t_payment_det  where pl_prop_num = '"+ProposalNumber+"' and PA_FLAG_TRANS='Y' AND PA_APPROVE='APP'");

			while (rs.next()) {
				PA_TRANS_ID = rs.getString("PA_TRANS_ID");

			}
			Reporter.log("<B>"+PA_TRANS_ID+"</B> fetched from portalindividual.t_payment_det for Policy Number = <B>"+ProposalNumber+"</B> and Cheque Number = <B>"+chequeNumber+"</B>");
			connectionWithQATPortal.close();
			Reporter.log("Connection Closed... ");
		}catch (Exception e) {
			e.printStackTrace();
			connectionWithQATPortal.close();
			Reporter.log("Connection Closed... ");
		}
		return PA_TRANS_ID;

	}
	//TODO Amiya modified 05/10/2018
	public void Insert_T_PAYMENT_FAIL_TEMP(Connection connectionWithIndigo,String transactionID) throws SQLException{
		try {
			Statement stmt1 = connectionWithIndigo.createStatement();
			if(ConfigReader.getInstance().getValue("ExecutionMode").equalsIgnoreCase("Migration")) {
				stmt1.executeQuery("Insert into INDIGO_DATA.T_PAYMENT_FAIL_TEMP(PA_TRANS_ID) Values (("+transactionID+"))");
			}else {
				stmt1.executeQuery("Insert into T_PAYMENT_FAIL_TEMP(PA_TRANS_ID) Values (("+transactionID+"))");	
			}

			Reporter.log("Transaction ID = <B>"+transactionID+"</B> inserted into T_PAYMENT_FAIL_TEMP");
			connectionWithIndigo.commit();
			connectionWithIndigo.close();
			Reporter.log("Connection Closed... ");
		}catch (Exception e) {
			e.printStackTrace();
			connectionWithIndigo.close();
			Reporter.log("Connection Closed... ");
		}

	}
	
	public void execute_PORTALTOXOSTAGINGPAYMENT_IND(Connection connectionWithIndigo, String ProposalNumber) throws SQLException{
		try {
			Reporter.log("Procedure <B>INDIGO_DATA.PORTALTOXOSTAGINGPAYMENT_IND</B> executing...");
			CallableStatement callableStatement = connectionWithIndigo.prepareCall("{CALL INDIGO_DATA.PORTALTOXOSTAGINGPAYMENT_IND (?,?)}");
			callableStatement.setString(1, ProposalNumber);
			callableStatement.setNull(2, Types.VARCHAR);
			boolean flag = callableStatement.execute();
			connectionWithIndigo.commit();
			Reporter.log("Procedure <B>INDIGO_DATA.PORTALTOXOSTAGINGPAYMENT_IND</B> executed successfully");
			connectionWithIndigo.close();
			Reporter.log("Connection Closed... ");
		}catch (Exception e) {
			e.printStackTrace();
			connectionWithIndigo.close();
			Reporter.log("Connection Closed... ");
		}

	}
	//TODO Amiya modified 05/10/2018
	public void executePaymentTransferBatch(Connection connectionWithUAT, String proposalNumber) throws SQLException {
		try {
			String query = "";
			if(ConfigReader.getInstance().getValue("ExecutionMode").equalsIgnoreCase("Migration")) {
				query="CALL PKG_TRANSFER_DATA_TO_ING.transfer_payment_data(?,?)";
			}else {
				query="CALL PKG_TRANSFER_DATA_TO_ING_QAT1.transfer_payment_data(?,?)";
			}
			CallableStatement callableStatement = connectionWithUAT.prepareCall(query);

			callableStatement.setString(1, proposalNumber);
			callableStatement.registerOutParameter(2, Types.VARCHAR);
			callableStatement.execute();
			connectionWithUAT.commit();
			if(ConfigReader.getInstance().getValue("ExecutionMode").equalsIgnoreCase("Migration")) {
				Reporter.log("procedure <B>PKG_TRANSFER_DATA_TO_ING.transfer_payment_data</B> executed successfully");query="CALL PKG_TRANSFER_DATA_TO_ING.transfer_payment_data(?,?)";
			}else {
				Reporter.log("procedure <B>PKG_TRANSFER_DATA_TO_ING_QAT1.transfer_payment_data</B> executed successfully");query="CALL PKG_TRANSFER_DATA_TO_ING_QAT1.transfer_payment_data(?,?)";
			}

			connectionWithUAT.close();
			Reporter.log("Connection Closed... ");
		}catch (Exception e) {
			e.printStackTrace();
			connectionWithUAT.close();
			Reporter.log("Connection Closed... ");
		}

	}
	//TODO Amiya modified 29/08/2018
	//TODO Amiya modified 05/10/2018
	//TODO Amiya modified 26/11/2018
	public Connection connection_With_Database_modified(XSSFWorkbook workbook, String sheetName, String databaseName) throws SQLException {
		try{

			Properties dataRow = ExcelRead.readRowDataForDatabaseAndPuttyConnection(workbook, sheetName, databaseName);
			Class.forName("oracle.jdbc.driver.OracleDriver");
			connection = DriverManager.getConnection("jdbc:oracle:thin:@"+dataRow.getProperty("HostName")+":"+dataRow.getProperty("Port")+":"+dataRow.getProperty("SID"),dataRow.getProperty("UserName"),dataRow.getProperty("Password"));
			connection.setAutoCommit(false);
			Reporter.log("Connection Successful...with "+databaseName);

		}catch (Exception e) {
			e.printStackTrace();
			connection.close();
			Reporter.log("Connection Closed... with "+databaseName);
		}
		return connection;
	}


	/*public Connection connection_For_QAT_Portal_Database (XSSFWorkbook workbook, String sheetName, String databaseName) {
		try{
			Properties dataRow = ExcelRead.readRowDataInProperties(workbook, sheetName, databaseName);
			Class.forName("oracle.jdbc.driver.OracleDriver");
			connection = DriverManager.getConnection("jdbc:oracle:thin:@"+dataRow.getProperty("HostName")+":"+dataRow.getProperty("Port")+":"+dataRow.getProperty("SID"),dataRow.getProperty("UserName"),dataRow.getProperty("Password"));
			connection.setAutoCommit(false);
			Reporter.log("Connection Successful... with Portal Database");

		}catch (Exception e) {
			e.printStackTrace();

		}
		return connection;
	}

	public Connection connection_For_UAT_Database(XSSFWorkbook workbook, String sheetName, String databaseName) {
		try{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			connection = DriverManager.getConnection("jdbc:oracle:thin:@172.16.19.18:1555:tb750UAT","KCAPXUAT","UATsbil_1");
			connection.setAutoCommit(false);
			Reporter.log("Connection Successful... with UAT Database");

		}catch (Exception e) {
			e.printStackTrace();
		}
		return connection;
	}

	public Connection connection_For_Indigo_Database() {
		try{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			connection = DriverManager.getConnection("jdbc:oracle:thin:@172.17.125.17:1521:indigo","indigo_data","indqat_123");
			connection.setAutoCommit(false);
			Reporter.log("Connection Successful... with Indigo Database");

		}catch (Exception e) {
			e.printStackTrace();
		}
		return connection;
	}

	 */
	//TODO Amiya modified 05/10/2018
	public void batch_9099_Client_Sync_Batch_Query(Connection connectionWithUAT, String date) throws SQLException {
		try {
			Statement statement = connectionWithUAT.createStatement();

			statement.executeUpdate("update tmast set BTCH_PRCES_DT ='"+date+"'");
			Reporter.log("<B>TMAST</B> table updated with date <B>"+date+"</B>");

			connectionWithUAT.commit();
			Reporter.log("Commit executed");
			connectionWithUAT.close();
			Reporter.log("Connection Closed... ");
		}catch (Exception e) {
			e.printStackTrace();
			connectionWithUAT.close();
			Reporter.log("Connection Closed... ");
		}

	}
	//TODO Amiya modified 05/10/2018
	public void batch_8082_Upload_Proposal_Batch_Query(Connection connectionWithUAT, String ProposalNumber, String BTCH_PRCES_DT) throws SQLException {
		try {
			Statement statement = connectionWithUAT.createStatement();

			statement.executeUpdate("Insert into S9082 select  POL_ID,'N' from  TZPOL where POl_ID in ('"+ProposalNumber+"')");
			Reporter.log("<B>S9082</B> table updated with Policy Number <B>"+ProposalNumber+"</B>");

			statement.executeUpdate("update tmast set BTCH_PRCES_DT ='"+BTCH_PRCES_DT+"'");
			Reporter.log("<B>TMAST</B> table updated with date <B>"+BTCH_PRCES_DT+"</B>");

			connectionWithUAT.commit();
			Reporter.log("Commit executed");
			connectionWithUAT.close();
			Reporter.log("Connection Closed... ");
		}catch (Exception e) {
			e.printStackTrace();
			connectionWithUAT.close();
			Reporter.log("Connection Closed... ");
		}

	}
	//TODO Amiya modified 05/10/2018
	public void batch_9071_Collection_Batch_Query(Connection connectionWithUAT, String BTCH_PRCES_DT) throws SQLException {
		try {
			Statement statement = connectionWithUAT.createStatement();

			statement.executeUpdate("update tmast set BTCH_PRCES_DT ='"+BTCH_PRCES_DT+"'");
			Reporter.log("<B>TMAST</B> table updated with date <B>"+BTCH_PRCES_DT+"</B>");

			connectionWithUAT.commit();
			Reporter.log("Commit executed");
			connectionWithUAT.close();
			Reporter.log("Connection Closed... ");
		}catch (Exception e) {
			e.printStackTrace();
			connectionWithUAT.close();
			Reporter.log("Connection Closed... ");
		}

	}

	//TODO Amiya modified 30/08/2018
	//TODO Amiya modified 05/10/2018
	public void batch_0201_Issuacnce_Batch_Query(Connection connectionWithUAT, String ProposalNumber, String date) throws SQLException {
		try {
			Statement ingeniumStatement = connectionWithUAT.createStatement();
			ingeniumStatement.execute("delete from s0201");
			Reporter.log("Existing Data deleted from <B>s0201</B> table");

			ResultSet rs = ingeniumStatement.executeQuery("Select CO_ID, POL_ID, PREV_UPDT_USER_ID, PREV_UPDT_TS,'N' from TPOL where  POl_ID  in ('"+ProposalNumber+"') and CO_id ='CP'");
			String CO_ID = "";
			String POL_ID = "";
			String PREV_UPDT_USER_ID = "";
			String PREV_UPDT_TS ="";
			String POL_PRCES_IND="";


			while (rs.next()) {
				CO_ID = rs.getString("CO_ID");
				POL_ID = rs.getString("POL_ID");
				PREV_UPDT_USER_ID = rs.getString("PREV_UPDT_USER_ID");
				PREV_UPDT_TS = rs.getString("PREV_UPDT_TS");
				POL_PRCES_IND = rs.getString("'N'");
			}

			ingeniumStatement.executeUpdate("insert into  S0201 values('"+CO_ID+"','"+POL_ID+"','"+ PREV_UPDT_USER_ID+"','"+PREV_UPDT_TS+"','"+POL_PRCES_IND+"')");
			//indigoStatement.executeUpdate("insert into  S0201@Indigo_Ingenium_preprod values('"+CO_ID+"','"+POL_ID+"','"+ PREV_UPDT_USER_ID+"','"+PREV_UPDT_TS+"','"+POL_PRCES_IND+"')");
			Reporter.log("Deatils for <B>"+ProposalNumber+"</B> is inserted into S0201 table");

			ingeniumStatement.executeUpdate("UPDATE TMAST SET BTCH_PRCES_DT ='"+date+"'");
			Reporter.log("<B>TMAST</B> table updated with date <B>"+date+"</B>");
			connectionWithUAT.commit();
			Reporter.log("Commit executed");

		}catch (Exception e) {
			e.printStackTrace();

		}
		finally {
			connectionWithUAT.close();
			Reporter.log("Connection Closed... ");
		}

	}

	//TODO Amiya modified 05/10/2018
	public void batch_9217_Revival_Batch_Query( Connection connectionWithUAT, String ProposalNumber, String date) throws SQLException {
		try {

			Statement ingeniumStatement = connectionWithUAT.createStatement();

			ingeniumStatement.execute("delete from s9217");
			Reporter.log("Existing Data deleted from <B>s9217</B> table");
			String CO_ID = "";
			String POL_ID = "";
			String PREV_UPDT_USER_ID = "";
			String PREV_UPDT_TS ="";
			String POL_PRCES_IND="N";
			String GRP_UPL_IND="N";
			ResultSet rs = ingeniumStatement.executeQuery("Select CO_ID, POL_ID, PREV_UPDT_USER_ID, PREV_UPDT_TS,'N' from TPOL where  POl_ID  in ('"+ProposalNumber+"') and CO_id ='CP'");

			if(ConfigReader.getInstance().getValue("ExecutionMode").equalsIgnoreCase("Migration")) {
				if(rs.next()) {
					CO_ID = rs.getString("CO_ID");
					POL_ID = rs.getString("POL_ID");
					PREV_UPDT_USER_ID = rs.getString("PREV_UPDT_USER_ID");
					PREV_UPDT_TS = rs.getString("PREV_UPDT_TS");
					//POL_PRCES_IND = rs.getString("'N'");
					ingeniumStatement.executeUpdate("insert into  S9217 values('"+CO_ID+"','"+POL_ID+"','"+ PREV_UPDT_USER_ID+"','"+PREV_UPDT_TS+"','"+POL_PRCES_IND+"')");
					Reporter.log("Deatils for <B>"+ProposalNumber+"</B> is inserted into S9217 table");
				}else {
					Reporter.log("Deatils for <B>"+ProposalNumber+"</B> are not fetched from TPOL table");
				}
			}else {
				if(rs.next()) {
					CO_ID = rs.getString("CO_ID");
					POL_ID = rs.getString("POL_ID");
					PREV_UPDT_USER_ID = rs.getString("PREV_UPDT_USER_ID");
					PREV_UPDT_TS = rs.getString("PREV_UPDT_TS");
					//POL_PRCES_IND = rs.getString("'N'");
					ingeniumStatement.executeUpdate("insert into  S9217 values('"+CO_ID+"','"+POL_ID+"','"+ PREV_UPDT_USER_ID+"','"+PREV_UPDT_TS+"','"+POL_PRCES_IND+"','"+GRP_UPL_IND+"')");

				}else {
					Reporter.log("Deatils for <B>"+ProposalNumber+"</B> are not fetched from TPOL table");
				}

			}
			ingeniumStatement.executeUpdate("UPDATE TMAST SET BTCH_PRCES_DT ='"+date+"'");
			Reporter.log("<B>TMAST</B> table updated with date <B>"+date+"</B>");

			connectionWithUAT.commit();
			Reporter.log("Commit executed");
			connectionWithUAT.close();
			Reporter.log("Connection Closed... ");

		}catch (Exception e) {
			connectionWithUAT.commit();
			e.printStackTrace();
			connectionWithUAT.close();
			Reporter.log("Connection Closed... ");
		}

	}

	//TODO Amiya added 12/09/2018
	//TODO Amiya modified 05/10/2018
	public String select_Premium_Suspense_Amount( Connection connectionWithUAT, String ProposalNumber) throws SQLException{
		String premiumSuspenseAmount="";
		try {

			Statement stmt = connectionWithUAT.createStatement();
			ResultSet rs = stmt.executeQuery("select POL_PREM_SUSP_AMT from tpol where pol_id ='"+ProposalNumber+"'");

			while (rs.next()) {
				premiumSuspenseAmount = rs.getString("POL_PREM_SUSP_AMT");
			}
			Reporter.log("<B>"+premiumSuspenseAmount+"</B> fetched from tpol for Policy Number = <B>"+ProposalNumber+"</B>");
			connectionWithUAT.close();
			Reporter.log("Connection Closed... ");
		}catch (Exception e) {
			e.printStackTrace();
			connectionWithUAT.close();
			Reporter.log("Connection Closed... ");
		}
		return premiumSuspenseAmount;
	}

	//TODO Amiya 14-09-2018
	//TODO Amiya modified 05/10/2018
	public void executeChequeRealisationBatch(Connection connectionWithUAT, String proposalno) throws SQLException {
		boolean flag;
		try {
			/*CallableStatement callableStatement = connectionWithIndigo.prepareCall("{PKG_TRANSFER_DATA_TO_ING_qat1.transfer_cda_data(?,?)}");
			callableStatement.setString(1, "ALL");
			callableStatement.setNull(2, Types.VARCHAR);
			callableStatement.setEscapeProcessing(false);
			boolean flag = callableStatement.execute();
			connectionWithIndigo.commit();
			Reporter.log("Procedure <B>PKG_TRANSFER_DATA_TO_ING_qat1.transfer_cda_data</B> executed successfully");
			connectionWithIndigo.close();*/

			/*String query="set serveroutput on;\r\n" + 
					"declare\r\n" + 
					"remarks varchar2(2000);\r\n" + 
					"begin\r\n" + 
					"  PKG_TRANSFER_DATA_TO_ING_qat1.transfer_cda_data('ALL',remarks);\r\n" + 
					"  dbms_output.put_line(remarks);\r\n" + 
					"end;";
			Statement ingeniumStatement = connectionWithIndigo.createStatement();

			boolean flag=ingeniumStatement.execute(query);
			System.out.println(flag);*/

			if(ConfigReader.getInstance().getValue("ExecutionMode").equalsIgnoreCase("Migration")) {
				CallableStatement callableStatement = connectionWithUAT.prepareCall("{call PKG_TRANSFER_DATA_TO_ING.transfer_cda_data(?,?)}");
				callableStatement.setString(1, "ALL");
				callableStatement.setString(2, "all");
				flag=callableStatement.execute();
				Reporter.log("SP for CDA Transfer to INGNENIUM");
			}else {
				CallableStatement callableStatement = connectionWithUAT.prepareCall("{call PKG_TRANSFER_DATA_TO_ING_qat1.transfer_cda_data(?,?)}");
				callableStatement.setString(1, "ALL");
				callableStatement.setString(2, "all");
				flag=callableStatement.execute();
				Reporter.log("SP for CDA Transfer to INGNENIUM");	
			}

			Statement ingeniumStatement = connectionWithUAT.createStatement();
			String query2="select LOG_COMMENTS from tzrdh where enr_no='"+proposalno+"'";
			ResultSet rs2=ingeniumStatement.executeQuery(query2);
			Reporter.log("Log Comments for Proposal Number: "+proposalno+" is as follows:");
			while (rs2.next()) {
				Reporter.log(rs2.getString("LOG_COMMENTS"));
			}

			connectionWithUAT.close();
			Reporter.log("Connection Closed... ");
		} catch(Exception e) {
			e.printStackTrace();
			connectionWithUAT.close();
			Reporter.log("Connection Closed... ");
		}
	}

	//TODO Amiya 14-09-2018
	//TODO Amiya modified 05/10/2018
	public void batch_9276_CDA_query(Connection connectionWithUAT) throws SQLException {
		try {
			Date date=new Date();

			SimpleDateFormat simpleDateFormat=new SimpleDateFormat("dd/MM/yyyy");
			String batchDate=simpleDateFormat.format(date);

			Statement statement = connectionWithUAT.createStatement();

			statement.executeUpdate("update tmast set BTCH_PRCES_DT ='"+batchDate+"'");
			Reporter.log("<B>TMAST</B> table updated with date <B>"+batchDate+"</B>");

			connectionWithUAT.commit();
			Reporter.log("Commit executed");
			connectionWithUAT.close();
			Reporter.log("Connection Closed... ");
		}catch (Exception e) {
			e.printStackTrace();
			connectionWithUAT.close();
			Reporter.log("Connection Closed... ");
		}

	}

	//TODO Amiya 27-09-2018
	//TODO Amiya modified 05/10/2018
	public String threadowner_IPAY(Connection connectionWithIndigo, String Threadid) throws SQLException{
		String nextloginid="";
		try {
			Statement statement = connectionWithIndigo.createStatement();
			ResultSet nextloginid1=statement.executeQuery("select wfowner from wfthreads@IPAY.SBILIFE.COM where WFTHREADID='"+Threadid+"'");
			while(nextloginid1.next()) {
				nextloginid=nextloginid1.getString("wfowner");
			}
			connectionWithIndigo.close();
			Reporter.log("Connection Closed... ");
		}catch (Exception e) {
			e.printStackTrace();
			connectionWithIndigo.close();
			Reporter.log("Connection Closed... ");
		}


		return nextloginid;
	}

	public void batch_0201_Lapsation(Connection connectionWithUAT, String ProposalNumber, String date) throws SQLException {
		try {
			Statement ingeniumStatement = connectionWithUAT.createStatement();
			ingeniumStatement.execute("delete from s0201");
			Reporter.log("Existing Data deleted from <B>s0201</B> table");

			//ingeniumStatement.executeUpdate("insert into  S0201 Select CO_ID, POL_ID, PREV_UPDT_USER_ID, PREV_UPDT_TS,'N' from TPOL  where  POl_ID  in ("+ProposalNumber+") and CO_id ='CP';");
			//indigoStatement.executeUpdate("insert into  S0201@Indigo_Ingenium_preprod values('"+CO_ID+"','"+POL_ID+"','"+ PREV_UPDT_USER_ID+"','"+PREV_UPDT_TS+"','"+POL_PRCES_IND+"')");

			ResultSet rs = ingeniumStatement.executeQuery("Select CO_ID, POL_ID, PREV_UPDT_USER_ID, PREV_UPDT_TS,'N' from TPOL where  POl_ID  in ("+ProposalNumber+") and CO_id ='CP'");
			String CO_ID = "";
			String POL_ID = "";
			String PREV_UPDT_USER_ID = "";
			String PREV_UPDT_TS ="";
			String POL_PRCES_IND="";


			while (rs.next()) {
				CO_ID = rs.getString("CO_ID");
				POL_ID = rs.getString("POL_ID");
				PREV_UPDT_USER_ID = rs.getString("PREV_UPDT_USER_ID");
				PREV_UPDT_TS = rs.getString("PREV_UPDT_TS");
				POL_PRCES_IND = rs.getString("'N'");
			}

			ingeniumStatement.executeUpdate("insert into  S0201 values('"+CO_ID+"','"+POL_ID+"','"+ PREV_UPDT_USER_ID+"','"+PREV_UPDT_TS+"','"+POL_PRCES_IND+"')");

			//ingeniumStatement.executeUpdate("insert into  S0201 Select CO_ID, POL_ID, PREV_UPDT_USER_ID, PREV_UPDT_TS,'N' from TPOL  where  POl_ID  in ("+ProposalNumber+") and CO_id ='CP';");
			//indigoStatement.executeUpdate("insert into  S0201@Indigo_Ingenium_preprod values('"+CO_ID+"','"+POL_ID+"','"+ PREV_UPDT_USER_ID+"','"+PREV_UPDT_TS+"','"+POL_PRCES_IND+"')");
			Reporter.log("Deatils for <B>"+ProposalNumber+"</B> is inserted into S0201 table");

			SimpleDateFormat dateformat=new SimpleDateFormat("dd/MM/yyyy");
			Date tdate=dateformat.parse(date);
			SimpleDateFormat format=new SimpleDateFormat("dd-MMM-yyyy");
			date=format.format(tdate);

			ingeniumStatement.executeUpdate("UPDATE TMAST SET BTCH_PRCES_DT ='"+date+"'");
			Reporter.log("<B>TMAST</B> table updated with date <B>"+date+"</B>");
			connectionWithUAT.commit();
			Reporter.log("Commit executed");

		}
		catch(Exception e) {
			Reporter.log(e.toString());
		}
		finally {
			connectionWithUAT.close();
			Reporter.log("Connection Closed... ");
		}
	}


	//TODO Amiya added 26/10/2018
	public void batch_9378_Refund_Batch_Query(Connection connectionWithUAT, String BTCH_PRCES_DT) throws SQLException {
		try {
			Statement statement = connectionWithUAT.createStatement();
			statement.executeUpdate("update tmast set BTCH_PRCES_DT ='"+BTCH_PRCES_DT+"'");
			Reporter.log("<B>TMAST</B> table updated with date <B>"+BTCH_PRCES_DT+"</B>");
			connectionWithUAT.commit();
			Reporter.log("Commit executed");
			connectionWithUAT.close();
			Reporter.log("Connection Closed... ");
		}catch (Exception e) {
			e.printStackTrace();
			connectionWithUAT.close();
			Reporter.log("Connection Closed... ");
		}
	}

	//TODO Amiya added 26/10/2018
	public void batch_9379_Refund_Batch_Query(Connection connectionWithUAT, String BTCH_PRCES_DT) throws SQLException {
		try {
			Statement statement = connectionWithUAT.createStatement();
			statement.executeUpdate("update tmast set BTCH_PRCES_DT ='"+BTCH_PRCES_DT+"'");
			Reporter.log("<B>TMAST</B> table updated with date <B>"+BTCH_PRCES_DT+"</B>");
			connectionWithUAT.commit();
			Reporter.log("Commit executed");
			connectionWithUAT.close();
			Reporter.log("Connection Closed... ");
		}catch (Exception e) {
			e.printStackTrace();
			connectionWithUAT.close();
			Reporter.log("Connection Closed... ");
		}
	}

	//TODO Amiya added 13/12/2018
	public void batch_9724_9729_maturityDueList_Batch_Query(Connection connectionWithUAT, String BTCH_PRCES_DT) throws SQLException {
		try {
			Statement statement = connectionWithUAT.createStatement();
			statement.executeUpdate("update tmast set BTCH_PRCES_DT ='"+BTCH_PRCES_DT+"'");
			Reporter.log("<B>TMAST</B> table updated with date <B>"+BTCH_PRCES_DT+"</B>");
			connectionWithUAT.commit();
			Reporter.log("Commit executed");
			/*connectionWithUAT.close();
			Reporter.log("Connection Closed... ");*/
		}catch (Exception e) {
			e.printStackTrace();
			/*connectionWithUAT.close();
			Reporter.log("Connection Closed... ");*/
		}
	}


	//TODO Amiya added 26/10/2018
	public String select_VCHR_STAT_CD_from_TZVHR(Connection connectionWithUAT, String ProposalNumber) throws SQLException{
		String VCHR_STAT_CD="";
		try {
			Statement stmt = connectionWithUAT.createStatement();
			ResultSet rs = stmt.executeQuery("select VCHR_STAT_CD from TZVHR where POL_ID='"+ProposalNumber+"'");
			while (rs.next()) {
				VCHR_STAT_CD = rs.getString("VCHR_STAT_CD");
			}
			Reporter.log("Voucher Status: <B>"+VCHR_STAT_CD+"</B> fetched from TZVHR for Policy Number = <B>"+ProposalNumber+"</B>");
			connectionWithUAT.close();
			Reporter.log("Connection Closed... ");
		}catch (Exception e) {
			e.printStackTrace();
			connectionWithUAT.close();
			Reporter.log("Connection Closed... ");
		}
		return VCHR_STAT_CD;
	}

	//TODO Amiya added 30/10/2018
	public String select_POL_ID_from_TPOL(Connection connectionWithUAT, String PolicyNumber) throws SQLException{
		String POL_ID="";
		try {
			Statement stmt = connectionWithUAT.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT POL_ID FROM TPOL where pol_ext_id='"+PolicyNumber+"'");
			while (rs.next()) {
				POL_ID = rs.getString("POL_ID");
			}
			Reporter.log("Policy ID: <B>"+POL_ID+"</B> fetched from TPOL for Policy Number = <B>"+PolicyNumber+"</B>");
			connectionWithUAT.close();
			Reporter.log("Connection Closed... ");
		}catch (Exception e) {
			e.printStackTrace();
			connectionWithUAT.close();
			Reporter.log("Connection Closed... ");
		}
		return POL_ID;
	}


	//TODO Amiya added 13/11/2018
	public String select_CLI_BTH_DT_from_TCLI(Connection connectionWithUAT, String clientID) throws SQLException{
		String CLI_BTH_DT="";
		try {
			Statement stmt = connectionWithUAT.createStatement();
			ResultSet rs = stmt.executeQuery("select CLI_BTH_DT from TCLI where cli_id = '"+clientID+"'");
			while (rs.next()) {
				CLI_BTH_DT = rs.getString("CLI_BTH_DT");
			}
			Reporter.log("Client's DOB: <B>"+CLI_BTH_DT+"</B> fetched from TCLI for Client ID = <B>"+clientID+"</B>");
			connectionWithUAT.close();
			Reporter.log("Connection Closed... ");
		}catch (Exception e) {
			e.printStackTrace();
			connectionWithUAT.close();
			Reporter.log("Connection Closed... ");
		}
		return CLI_BTH_DT;
	}

	//TODO Amiya added
	//TODO Amiya modified 26/11/2016
	public Connection connection_With_Database(XSSFWorkbook workbook, String sheetName, String databaseName) throws SQLException {
		try{
			if(ConfigReader.getInstance().getValue("ExecutionMode").equalsIgnoreCase("Migration")) {
				switch (databaseName) {
				case "Ingenium_UAT":
					databaseName="Migration_UAT2";
					break;

				case "QAT_Indigo":
					databaseName="Migration_Indigo";
					break;

				default:
					break;
				}
			}
			Properties dataRow = ExcelRead.readRowDataForDatabaseAndPuttyConnection(workbook, sheetName, databaseName);
			Class.forName("oracle.jdbc.driver.OracleDriver");
			connection = DriverManager.getConnection("jdbc:oracle:thin:@"+dataRow.getProperty("HostName")+":"+dataRow.getProperty("Port")+":"+dataRow.getProperty("SID"),dataRow.getProperty("UserName"),dataRow.getProperty("Password"));
			connection.setAutoCommit(false);
			Reporter.log("Connection Successful... with "+databaseName);

		}catch (Exception e) {
			e.printStackTrace();
			connection.close();
			Reporter.log("Connection Closed... with "+databaseName);
		}
		return connection;
	}


	//TODO Amiya added 13/11/2018
	public void verify_PRTX_NULL(Connection connectionWithUAT, String proposalNumber, CustomAssert customAssert) throws SQLException{
		try {
			Statement stmt = connectionWithUAT.createStatement();
			ResultSet rs = stmt.executeQuery("select * from TPRTX where POL_ID = '"+proposalNumber+"' and TRNXT_PRCES_DT='"+new SimpleDateFormat("dd/MM/yyyy").format(new Date())+"'");
			if(rs.next()==false) {
				customAssert.verifyAssert(false, rs.next(), "Verify no record present");
				Reporter.log("No record found for proposal number: <B>"+proposalNumber+"</B> in TPRTX Table");
			}else {
				throw new CustomException("Record found for Proposal Number: <B>"+proposalNumber+"</B> in TPRTX table");
			}

			connectionWithUAT.close();
			Reporter.log("Connection Closed... ");
		}catch (Exception e) {
			e.printStackTrace();
			connectionWithUAT.close();
			Reporter.log("Connection Closed... ");
		}	
	}
	//TODO Amiya added 13/11/2018
	public void verify_ACTX_NULL(Connection connectionWithUAT, CustomAssert customAssert, String proposalNumber) throws SQLException{
		try {
			Statement stmt = connectionWithUAT.createStatement();
			ResultSet rs = stmt.executeQuery("select * from TACTX where POL_ID = '"+proposalNumber+"' and ACCT_DESC_REASN_CD='DUPP' and ACCT_TRXN_PRCES_DT='"+new SimpleDateFormat("dd/MM/yyyy").format(new Date())+"'");
			if(rs.next()==false) {
				customAssert.verifyAssert(false, rs.next(), "Verify no record present");
				Reporter.log("No record found for proposal number: <B>"+proposalNumber+"</B> in TACTX Table");	
			}	
			else {
				throw new CustomException("Record found for Proposal Number: <B>"+proposalNumber+"</B> in TACTX Table");
			}
			connectionWithUAT.close();
			Reporter.log("Connection Closed... ");
		}catch (Exception e) {
			e.printStackTrace();
			connectionWithUAT.close();
			Reporter.log("Connection Closed... ");
		}

	}
	//TODO Amiya added 13/11/2018
	public void verify_PRTX(Connection connectionWithUAT,  CustomAssert customAssert, String proposalNumber, String docIDMessage) throws SQLException{
		try {
			String DOC_ID="";
			Statement stmt = connectionWithUAT.createStatement();
			Thread.sleep(WaitTime.low);
			ResultSet rs = stmt.executeQuery("select * from TPRTX where POL_ID = '"+proposalNumber+"' and TRNXT_PRCES_DT='"+new SimpleDateFormat("dd-MM-yy").format(new Date())+"'");
			if(rs.next()==false) {
				Reporter.log("No record found for proposal number: <B>"+proposalNumber+"</B> in TPRTX Table");	
				customAssert.verifyAssert(true, rs.next(), "Verify TPRTX Table Entry");
				throw new CustomException("Record found for Proposal Number: <B>"+proposalNumber+"</B> in TPRTX Table");
			}	
			else {
				do {
					DOC_ID = rs.getString("DOC_ID").trim();
					//customAssert.verifyAssert(docIDMessage, DOC_ID, "Verify Duplicate Policy charge");
				}
				while (rs.next());
			}
			connectionWithUAT.close();
			Reporter.log("Connection Closed... ");
		}catch (Exception e) {
			e.printStackTrace();
			connectionWithUAT.close();
			Reporter.log("Connection Closed... ");
		}
	}
	//TODO Amiya added 13/11/2018
	public void verify_ACTX(Connection connectionWithUAT,CustomAssert customAssert,  String proposalNumber) throws SQLException{
		try {
			Statement stmt = connectionWithUAT.createStatement();
			Thread.sleep(WaitTime.low);
			ResultSet rs = stmt.executeQuery("select * from TACTX where POL_ID = '"+proposalNumber+"' and ACCT_DESC_REASN_CD='DUPP' and ACCT_TRXN_PRCES_DT='"+new SimpleDateFormat("dd-MM-yy").format(new Date())+"'");
			if(rs.next()==false) {
				Reporter.log("No record found for proposal number: <B>"+proposalNumber+"</B> in TACTX Table");
				customAssert.verifyAssert(true, rs.next(), "Verify TACTX Table Entry");
				throw new CustomException("Record found for Proposal Number: <B>"+proposalNumber+"</B> in TACTX Table");

			}	
			else {
				do {
					Reporter.log(rs.getString("ACCT_DESC_TXT")+": "+ rs.getString("ACCT_TRXN_AMT"));
				}
				while (rs.next());

				rs = stmt.executeQuery("select * from TACTX where POL_ID = '"+proposalNumber+"' and ACCT_DESC_REASN_CD='SGST' and ACCT_TRXN_PRCES_DT='"+new SimpleDateFormat("dd/MM/yyyy").format(new Date())+"'");
				while (rs.next()) {
					Reporter.log(rs.getString("ACCT_DESC_TXT")+": "+ rs.getString("ACCT_TRXN_AMT"));
				}
				rs = stmt.executeQuery("select * from TACTX where POL_ID = '"+proposalNumber+"' and ACCT_DESC_REASN_CD='CGST' and ACCT_TRXN_PRCES_DT='"+new SimpleDateFormat("dd/MM/yyyy").format(new Date())+"'");
				while (rs.next()) {
					Reporter.log(rs.getString("ACCT_DESC_TXT")+": "+ rs.getString("ACCT_TRXN_AMT"));
				}
				rs = stmt.executeQuery("select * from TACTX where POL_ID = '"+proposalNumber+"' and ACCT_DESC_REASN_CD='IGST' and ACCT_TRXN_PRCES_DT='"+new SimpleDateFormat("dd/MM/yyyy").format(new Date())+"'");
				while (rs.next()) {
					Reporter.log(rs.getString("ACCT_DESC_TXT")+": "+ rs.getString("ACCT_TRXN_AMT"));
				}
				rs = stmt.executeQuery("select * from TACTX where POL_ID = '"+proposalNumber+"' and ACCT_DESC_REASN_CD='STMP' and ACCT_TRXN_PRCES_DT='"+new SimpleDateFormat("dd/MM/yyyy").format(new Date())+"'");
				while (rs.next()) {
					Reporter.log(rs.getString("ACCT_DESC_TXT")+": "+ rs.getString("ACCT_TRXN_AMT"));
				}
			}
			connectionWithUAT.close();
			Reporter.log("Connection Closed... ");
		}catch (Exception e) {
			e.printStackTrace();
			connectionWithUAT.close();
			Reporter.log("Connection Closed... ");
		}
	}

	//TODO Amiya 04-12-2018
	public void batch_9695_SBduelist(Connection connectionWithUAT, String date) throws SQLException {
		try {
			Statement statement = connectionWithUAT.createStatement();

			statement.executeUpdate("update tmast set BTCH_PRCES_DT ='"+date+"'");
			Reporter.log("<B>TMAST</B> table updated with date <B>"+date+"</B>");

			connectionWithUAT.commit();
			Reporter.log("Commit executed");
			//			connectionWithUAT.close();
			//			Reporter.log("Connection Closed... ");
		}catch (Exception e) {
			e.printStackTrace();

		}
	}
	//TODO Amiya modified 05/10/2018
	public void batch_9555_Batch_Query(Connection connectionWithUAT, String BTCH_PRCES_DT) throws SQLException {
		try {
			Statement statement = connectionWithUAT.createStatement();
			statement.executeUpdate("update tmast set BTCH_PRCES_DT ='"+BTCH_PRCES_DT+"'");
			Reporter.log("<B>TMAST</B> table updated with date <B>"+BTCH_PRCES_DT+"</B>");
			connectionWithUAT.commit();
			Reporter.log("Commit executed");
			connectionWithUAT.close();
			Reporter.log("Connection Closed... ");
		}catch (Exception e) {
			e.printStackTrace();
			connectionWithUAT.close();
			Reporter.log("Connection Closed... ");
		}
	}
	//TODO Amiya added 22/03/2019
	public void verify_TPRTX_REDIRCONF(Connection connectionWithUAT,  CustomAssert customAssert, String proposalNumber, String docIDMessage, String date) throws SQLException{
		try {
			String DOC_ID="";
			Statement stmt = connectionWithUAT.createStatement();
			Thread.sleep(WaitTime.low);
			ResultSet rs = stmt.executeQuery("select * from TPRTX where POL_ID = '"+proposalNumber+"' and TRNXT_PRCES_DT='"+date+"' and DOC_ID='"+docIDMessage+"'");
			if(rs.next()==false) {
				Reporter.log("No record found for proposal number: <B>"+proposalNumber+"</B> in TPRTX Table");	
				customAssert.verifyAssert(true, rs.next(), "Verify TPRTX Table Entry");
				throw new CustomException("Record found for Proposal Number: <B>"+proposalNumber+"</B> in TPRTX Table");
			}	
			else {
				do {
					DOC_ID = rs.getString("DOC_ID").trim();
					//customAssert.verifyAssert(docIDMessage, DOC_ID, "Verify Redirection Confirmation Notice");
				}
				while (rs.next());
			}
			connectionWithUAT.close();
			Reporter.log("Connection Closed... ");
		}catch (Exception e) {
			e.printStackTrace();
			connectionWithUAT.close();
			Reporter.log("Connection Closed... ");
		}
	}
	//TODO Amiya added 22/03/2019
	public void VerifyTPRTX_REDIRREJ(Connection connectionWithUAT,  CustomAssert customAssert, String proposalNumber, String docIDMessage, String date) throws SQLException{
		try {
			String DOC_ID="";
			Statement stmt = connectionWithUAT.createStatement();
			Thread.sleep(WaitTime.low);
			ResultSet rs = stmt.executeQuery("select * from TPRTX where POL_ID = '"+proposalNumber+"' and TRNXT_PRCES_DT='"+date+"' and DOC_ID='"+docIDMessage+"'");
			if(rs.next()==false) {
				Reporter.log("No record found for proposal number: <B>"+proposalNumber+"</B> in TPRTX Table");	
				customAssert.verifyAssert(true, rs.next(), "Verify TPRTX Table Entry");
				throw new CustomException("Record found for Proposal Number: <B>"+proposalNumber+"</B> in TPRTX Table");
			}	
			else {
				do {
					DOC_ID = rs.getString("DOC_ID").trim();
					//customAssert.verifyAssert(docIDMessage, DOC_ID, "Verify Redirection Confirmation Notice");
				}
				while (rs.next());
			}
			connectionWithUAT.close();
			Reporter.log("Connection Closed... ");
		}catch (Exception e) {
			e.printStackTrace();
			connectionWithUAT.close();
			Reporter.log("Connection Closed... ");
		}
	}


	//TODO Amiya modified 03/04/2019
	public String DataFromTPODP(Connection connectionWithUAT, String proposalNumber , String dataFromTPODP) throws SQLException{
		String DataFromTPODP="";
		try {
			Statement stmt = connectionWithUAT.createStatement();
			//TODO Amiya modified 11/04/2019
			ResultSet rs = stmt.executeQuery("select "+dataFromTPODP+" from TPODP where pol_id ='"+proposalNumber+"'");
			while (rs.next()) {
				DataFromTPODP = rs.getString(dataFromTPODP);
			}
			Reporter.log("<B> '"+DataFromTPODP+"'</B> fetched from TPODP for Policy Number = <B>"+proposalNumber+"</B>");
			connectionWithUAT.close();
			Reporter.log("Connection Closed... ");
		}catch (Exception e) {
			e.printStackTrace();
			connectionWithUAT.close();
			Reporter.log("Connection Closed... ");
		}

		if(DataFromTPODP.contains("-")) {
			DataFromTPODP=DataFromTPODP.replaceAll("-", "/");
		}

		if(DataFromTPODP.contains(" ")) {
			String Dt1[]=DataFromTPODP.split(" ");
			String Dt2[]=Dt1[0].split("/");
			if(Dt2[0].length()==4) {
				String yr=Dt2[0];
				Dt2[0]=Dt2[2];
				Dt2[2]=yr;
			}
			DataFromTPODP=Dt2[0]+"/"+Dt2[1]+"/"+Dt2[2];
		}
		return DataFromTPODP;
	}


	//TODO Amiya added 03/04/2019
	public String selectDataFromTPOL(Connection connectionWithUAT, String PolicyNumber, String ColumnName) throws SQLException{
		String POL_Status="";
		try {
			Statement stmt = connectionWithUAT.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT "+ColumnName+" FROM TPOL where POL_ID='"+PolicyNumber+"'");
			while (rs.next()) {
				//TODO Amiya modified 11/4/2019
				POL_Status = rs.getString(ColumnName).trim();
			}
			Reporter.log("Policy ID: <B>"+POL_Status+"</B> fetched from TPOL for Policy Number = <B>"+PolicyNumber+"</B>");
			connectionWithUAT.close();
			Reporter.log("Connection Closed... ");
		}catch (Exception e) {
			e.printStackTrace();
			connectionWithUAT.close();
			Reporter.log("Connection Closed... ");
		}
		return POL_Status;
	}

	//TODO Prandya modified 11/04/2019
	public void batch_9696_Batch_Query(Connection connectionWithUAT, String BTCH_PRCES_DT) throws SQLException {
		try {
			Statement statement = connectionWithUAT.createStatement();
			statement.executeUpdate("update tmast set BTCH_PRCES_DT ='"+BTCH_PRCES_DT+"'");
			Reporter.log("<B>TMAST</B> table updated with date <B>"+BTCH_PRCES_DT+"</B>");
			connectionWithUAT.commit();
			Reporter.log("Commit executed");
			connectionWithUAT.close();
			Reporter.log("Connection Closed... ");
		}catch (Exception e) {
			e.printStackTrace();
			connectionWithUAT.close();
			Reporter.log("Connection Closed... ");
		}
	}

	// TODO Amiya Added 15/04/2019

	public String DateFromTPOL(Connection connectionWithUAT, String proposalNumber , String dataFromTPOL) throws SQLException{
		String DataFromTPOL="";
		try {
			Statement stmt = connectionWithUAT.createStatement();

			ResultSet rs = stmt.executeQuery("select "+dataFromTPOL+" from TPOL where pol_id ='"+proposalNumber+"'");
			while (rs.next()) {
				DataFromTPOL = rs.getString(dataFromTPOL);
			}
			Reporter.log("<B> '"+DataFromTPOL+"'</B> fetched from TPOL for Policy Number = <B>"+proposalNumber+"</B>");
			connectionWithUAT.close();
			Reporter.log("Connection Closed... ");
		}catch (Exception e) {
			e.printStackTrace();
			connectionWithUAT.close();
			Reporter.log("Connection Closed... ");
		}

		if(DataFromTPOL.contains("-")) {
			DataFromTPOL=DataFromTPOL.replaceAll("-", "/");
		}

		if(DataFromTPOL.contains(" ")) {
			String Dt1[]=DataFromTPOL.split(" ");
			String Dt2[]=Dt1[0].split("/");
			if(Dt2[0].length()==4) {
				String yr=Dt2[0];
				Dt2[0]=Dt2[2];
				Dt2[2]=yr;
			}
			if(Dt2[2].length()==2) {
				Dt2[2]="20"+Dt2[2];
			}
			DataFromTPOL=Dt2[0]+"/"+Dt2[1]+"/"+Dt2[2];
		}
		return DataFromTPOL;
	}

	public void updateDataInTCVG(Connection connectionWithUAT, String proposalNumber , String changeDate, String coverageNumber) throws Exception {
		Statement ingeniumStatement = connectionWithUAT.createStatement();
		ingeniumStatement.executeUpdate("UPDATE TCVG SET CVG_PREM_CHNG_DT ='"+changeDate+"', CVG_MAT_XPRY_DT='"+changeDate+"' where pol_id='"+proposalNumber+"' and cvg_num='"+coverageNumber+"'");
		Reporter.log("<B>TCVG</B> table updated with date <B>"+changeDate+"</B>");
		connectionWithUAT.commit();
		Reporter.log("Commit executed");
		connectionWithUAT.close();
	}

	//TODO Amiya 24-04-2019
	public void printDatafromTZVHRforAnnuity(Connection connectionWithUAT, String ProposalNumber, String processdate) throws SQLException {
		Statement ingeniumStatement = connectionWithUAT.createStatement();
		try {
			ResultSet rs = ingeniumStatement.executeQuery("select POL_ID,ANPAYO_DUE_DT,ANPAYO_AMT,VCHR_STAT_CD,PRCES_DT from tzvhr where pol_id='"+ProposalNumber+"' and ANPAYO_DUE_DT='"+processdate+"' ");
			String POL_ID = "";
			String ANPAYO_DUE_DT = "";
			String ANPAYO_AMT = "";
			String VCHR_STAT_CD ="";
			String PRCES_DT="";


			while (rs.next()) {
				POL_ID = rs.getString("POL_ID");
				Reporter.log("<B>Data from TZVHR table for "+POL_ID+"</B>");
				ANPAYO_DUE_DT = rs.getString("ANPAYO_DUE_DT");
				Reporter.log("Annuity Payout Date: "+ANPAYO_DUE_DT);
				ANPAYO_AMT = rs.getString("ANPAYO_AMT");
				Reporter.log("Annuity Payout Amount: "+ANPAYO_AMT);
				VCHR_STAT_CD = rs.getString("VCHR_STAT_CD");
				Reporter.log("Voucher Status: "+VCHR_STAT_CD);
				PRCES_DT = rs.getString("PRCES_DT");
				Reporter.log("Process Date: "+PRCES_DT);
			}
			
			connectionWithUAT.close();
			Reporter.log("Connection Closed... ");
		}
		catch (Exception e) {
			e.printStackTrace();
			connectionWithUAT.close();
			Reporter.log("Connection Closed... ");
		}
	}
}
