package edu.ucsd.som.stip.session;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.PrintSetup;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Logger;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;
import org.jboss.seam.log.Log;

import edu.ucsd.som.stip.beans.FiscalRpt;
import edu.ucsd.som.stip.services.FiscalYearRptRepository;

@Name("fiscalYearRptList")
@Scope(ScopeType.CONVERSATION)
public class FiscalYearReportList  implements Serializable{


	private static final long serialVersionUID = -8844129699125999614L;

	
	@In
	private EntityManager entityManager;
	@Logger
	private Log log;
	@In BadgIdentity identity;
	
	private String selectedFiscalYear;
	
	private BigDecimal q1AllIndxTotal;
	private BigDecimal q2AllIndxTotal;
	private BigDecimal q3AllIndxTotal;
	private BigDecimal q4AllIndxTotal;
	private BigDecimal allIndxTotalsTotal;


	private FiscalRpt fiscalYearRpt = new FiscalRpt();
	
	public FiscalYearReportList() {
	}

	public FiscalRpt getFiscalYearRpt() {
		return fiscalYearRpt;
	}
	
	
	@SuppressWarnings("unchecked")
	public List<FiscalRpt> getResultList()
	{
		
		this.q1AllIndxTotal=BigDecimal.ZERO;
		this.q2AllIndxTotal=BigDecimal.ZERO;
		this.q3AllIndxTotal=BigDecimal.ZERO;
		this.q4AllIndxTotal=BigDecimal.ZERO;
		this.allIndxTotalsTotal=BigDecimal.ZERO;
		
		if(selectedFiscalYear!=null)
		{
			try{
				String majorGrouplcl=null;
				if(fiscalYearRpt.getMajorGroup()!=null )
					majorGrouplcl="'"+this.fiscalYearRpt.getMajorGroup()+"'";
				else
					majorGrouplcl="'%'";
				String qtrlcl="'"+this.selectedFiscalYear+"%'";
				List<Object[]> nativeList= (List<Object[]>)entityManager.createNativeQuery(FiscalYearRptRepository.getFiscalYearRptQuery(majorGrouplcl, qtrlcl)).
									        getResultList();
				
				List<FiscalRpt> returnList=new ArrayList<FiscalRpt>();
				for(Object[] o:nativeList)
				{
					FiscalRpt rpt=new FiscalRpt();
					rpt.setMajorGroup((String) o[0]);
					rpt.setDepartment((String) o[1]);
					rpt.setIndexGenerated((String) o[2]);
					rpt.setFundGenerated((String) o[3]);
					rpt.setIndexDeposited((String) o[4]);
					rpt.setFundDeposited((String) o[5]);
					rpt.setQuartersList((String) o[6]);
					rpt.setQuartersStipList((String) o[7]);
					rpt.setTotalFiscalStip(BigDecimal.valueOf((Double) o[8]));
					
					List<String> qrts=Arrays.asList(rpt.getQuartersList().split(","));
					List<String> qrtsStip=Arrays.asList(rpt.getQuartersStipList().split(","));
					
					for(int i=0;i<qrts.size();i++)
					{
						if(qrts.get(i).contains("Q1"))
						{
							rpt.setQ1total(BigDecimal.valueOf(Double.parseDouble(qrtsStip.get(i))));
							this.q1AllIndxTotal=this.getQ1AllIndxTotal().add(rpt.getQ1total());
						}
						else if(qrts.get(i).contains("Q2"))
						{
							rpt.setQ2total(BigDecimal.valueOf(Double.parseDouble(qrtsStip.get(i))));
							this.q2AllIndxTotal=this.getQ2AllIndxTotal().add(rpt.getQ2total());
						}
						else if(qrts.get(i).contains("Q3"))
						{
							rpt.setQ3total(BigDecimal.valueOf(Double.parseDouble(qrtsStip.get(i))));
							this.q3AllIndxTotal=this.getQ3AllIndxTotal().add(rpt.getQ3total());
						}
						else if(qrts.get(i).contains("Q4"))
						{
							rpt.setQ4total(BigDecimal.valueOf(Double.parseDouble(qrtsStip.get(i))));
							this.q4AllIndxTotal=this.getQ4AllIndxTotal().add(rpt.getQ4total());
						}
					}
					this.allIndxTotalsTotal=this.getQ1AllIndxTotal().add(this.getQ2AllIndxTotal().add(this.getQ3AllIndxTotal().add(this.getQ4AllIndxTotal())));
					returnList.add(rpt);
				}
				
				if(!returnList.isEmpty())
				{
					FiscalRpt rpt=new FiscalRpt();
					rpt.setIndexGenerated("zz");
					rpt.setFundDeposited("Totals:");
					rpt.setQ1total(q1AllIndxTotal);
					rpt.setQ2total(q2AllIndxTotal);
					rpt.setQ3total(q3AllIndxTotal);
					rpt.setQ4total(q4AllIndxTotal);
					rpt.setTotalFiscalStip(allIndxTotalsTotal);
					returnList.add(rpt);
				}
				
				return returnList;
				
			}catch(Exception ex)
			{
				ex.printStackTrace();
			}
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	public List<String> getFiscalYearList()
	{
		return entityManager.createQuery(FiscalYearRptRepository.fiscalYearList).getResultList();
	}
	
	@SuppressWarnings("unchecked")
	public List<String> getMajorGrouplist()
	{
		if(identity.hasRole("stip_admin"))
		return entityManager.createQuery(FiscalYearRptRepository.majorGrouplistAdmin).
								setParameter("qrt",selectedFiscalYear+"%" ).getResultList();
		else if(identity.hasRole("dbo"))
			return entityManager.createQuery(FiscalYearRptRepository.majorGrouplistDbo).
									setParameter("qrt",selectedFiscalYear+"%" ).
									setParameter("accountId", identity.getAccountId()).
									getResultList();
		else
			return null;
	}
	

    private static final String[] titles = {
            "Division",	"Index Generated", "Fund Generated", "Index Deposited", "Fund Deposited", "Q1", "Q2", "Q3", "Q4",
            "Total"
    };


    public void generateRpt() throws Exception {
    	
    	List<FiscalRpt> rptList = this .getResultList();
    	
    	if(this.selectedFiscalYear!=null && !rptList.isEmpty())
    	{
    		
    		rptList.remove(rptList.size()-1);
    		
    		HSSFWorkbook wb= new HSSFWorkbook();

            Map<String, CellStyle> styles = createStyles(wb);

            Sheet sheet = wb.createSheet("fiscal_yr_summary_"+this.selectedFiscalYear);
            PrintSetup printSetup = sheet.getPrintSetup();
            printSetup.setLandscape(true);
            sheet.setFitToPage(true);
            sheet.setHorizontallyCenter(true);

            //title row
            Row titleRow = sheet.createRow(0);
            titleRow.setHeightInPoints(45);
            Cell titleCell = titleRow.createCell(0);
            titleCell.setCellValue("Fiscal Year Summary For "+ this.selectedFiscalYear);
            titleCell.setCellStyle(styles.get("title"));
            sheet.addMergedRegion(CellRangeAddress.valueOf("$A$1:$J$1"));

            //header row
            Row headerRow = sheet.createRow(1);
            headerRow.setHeightInPoints(40);
            Cell headerCell;
            for (int i = 0; i < titles.length; i++) {
                headerCell = headerRow.createCell(i);
                headerCell.setCellValue(titles[i]);
                headerCell.setCellStyle(styles.get("header"));
            }

            int rownum = 2;
            for (int i = 0; i < rptList.size(); i++) {
                Row row = sheet.createRow(rownum++);
                for (int j = 0; j < titles.length; j++) {
                    Cell cell = row.createCell(j);
                    if(j == 9){
                        //the 10th cell contains sum over quarters, e.g. SUM(C3:I3)
                        String ref = "F" +rownum+ ":I" + rownum;
                        cell.setCellFormula("SUM("+ref+")");
                        cell.setCellStyle(styles.get("formula"));
                    } else {
                        cell.setCellStyle(styles.get("cell"));
                    }
                }
            }

            //row with totals below
            Row sumRow = sheet.createRow(rownum++);
            sumRow.setHeightInPoints(35);
            Cell cell;
            cell = sumRow.createCell(0);
            cell.setCellStyle(styles.get("formula"));
            cell = sumRow.createCell(1);
            cell.setCellValue("Total Stip:");
            cell.setCellStyle(styles.get("formula"));
            cell = sumRow.createCell(2);
            cell.setCellStyle(styles.get("formula"));
            cell = sumRow.createCell(3);
            cell.setCellStyle(styles.get("formula"));
            cell = sumRow.createCell(4);
            cell.setCellStyle(styles.get("formula"));

            String lastRowIdx=String.valueOf(rptList.size()+2);
            
            for (int j = 5; j < 10; j++) {
                cell = sumRow.createCell(j);
                String ref = (char)('A' + j) + "3:" + (char)('A' + j) + lastRowIdx;
                cell.setCellFormula("SUM(" + ref + ")");
                if(j >= 9) cell.setCellStyle(styles.get("formula_2"));
                else cell.setCellStyle(styles.get("formula"));
            }
           

            //set sample data
            for (int i = 0; i < rptList.size(); i++) {
                Row row = sheet.getRow(2 + i);
                
                row.getCell(0).setCellValue(rptList.get(i).getDepartment());
                row.getCell(1).setCellValue(rptList.get(i).getIndexGenerated());
                row.getCell(2).setCellValue(rptList.get(i).getFundGenerated());
                row.getCell(3).setCellValue(rptList.get(i).getIndexDeposited());
                row.getCell(4).setCellValue(rptList.get(i).getFundDeposited());
                row.getCell(5).setCellValue(rptList.get(i).getQ1total().doubleValue());
                row.getCell(5).getCellStyle().setDataFormat(wb.createDataFormat().getFormat("0.00"));
                row.getCell(6).setCellValue(rptList.get(i).getQ2total().doubleValue());
                row.getCell(6).getCellStyle().setDataFormat(wb.createDataFormat().getFormat("0.00"));
                row.getCell(7).setCellValue(rptList.get(i).getQ3total().doubleValue());
                row.getCell(7).getCellStyle().setDataFormat(wb.createDataFormat().getFormat("0.00"));
                row.getCell(8).setCellValue(rptList.get(i).getQ4total().doubleValue());
                row.getCell(8).getCellStyle().setDataFormat(wb.createDataFormat().getFormat("0.00"));
                
            }

            //finally set column widths, the width is measured in units of 1/256th of a character width
            sheet.setColumnWidth(0, 30*256); //30 characters wide for division
            for (int i = 1; i < 5; i++) {
                sheet.setColumnWidth(i, 15*256);  //15 characters wide for funds/indices
            }
            for (int i = 5; i < 10; i++) {
                sheet.setColumnWidth(i,13*256);  //8 characters wide for totals
            }

            HttpServletResponse response  = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
            ServletOutputStream servletOutputStreamObj = response.getOutputStream();
            response.setContentType("application/vnd.ms-excel");
            response.setHeader("Content-Disposition", "attachment; filename=\"" + "fiscal_yr_rpt_"+this.getSelectedFiscalYear() + ".xls\"");
            wb.write(servletOutputStreamObj);
    		servletOutputStreamObj.flush();
    		servletOutputStreamObj.close();
    		response.flushBuffer();
    		FacesContext.getCurrentInstance().responseComplete();
    	}
    	
    	
    }

    /**
     * Create a library of cell styles
     */
    private static Map<String, CellStyle> createStyles(Workbook wb){
        Map<String, CellStyle> styles = new HashMap<String, CellStyle>();
        CellStyle style;
        Font titleFont = wb.createFont();
        titleFont.setFontHeightInPoints((short)18);
        titleFont.setBoldweight(Font.BOLDWEIGHT_BOLD);
        style = wb.createCellStyle();
        style.setAlignment(CellStyle.ALIGN_CENTER);
        style.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
        style.setFont(titleFont);
        styles.put("title", style);

        Font monthFont = wb.createFont();
        monthFont.setFontHeightInPoints((short)11);
        monthFont.setColor(IndexedColors.WHITE.getIndex());
        style = wb.createCellStyle();
        style.setAlignment(CellStyle.ALIGN_CENTER);
        style.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
        style.setFillForegroundColor(IndexedColors.GREY_50_PERCENT.getIndex());
        style.setFillPattern(CellStyle.SOLID_FOREGROUND);
        style.setFont(monthFont);
        style.setWrapText(true);
        styles.put("header", style);

        style = wb.createCellStyle();
        style.setAlignment(CellStyle.ALIGN_CENTER);
        style.setWrapText(true);
        style.setBorderRight(CellStyle.BORDER_THIN);
        style.setRightBorderColor(IndexedColors.BLACK.getIndex());
        style.setBorderLeft(CellStyle.BORDER_THIN);
        style.setLeftBorderColor(IndexedColors.BLACK.getIndex());
        style.setBorderTop(CellStyle.BORDER_THIN);
        style.setTopBorderColor(IndexedColors.BLACK.getIndex());
        style.setBorderBottom(CellStyle.BORDER_THIN);
        style.setBottomBorderColor(IndexedColors.BLACK.getIndex());
        styles.put("cell", style);

        style = wb.createCellStyle();
        style.setAlignment(CellStyle.ALIGN_CENTER);
        style.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
        style.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
        style.setFillPattern(CellStyle.SOLID_FOREGROUND);
        style.setDataFormat(wb.createDataFormat().getFormat("0.00"));
        styles.put("formula", style);

        style = wb.createCellStyle();
        style.setAlignment(CellStyle.ALIGN_CENTER);
        style.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
        style.setFillForegroundColor(IndexedColors.GREY_40_PERCENT.getIndex());
        style.setFillPattern(CellStyle.SOLID_FOREGROUND);
        style.setDataFormat(wb.createDataFormat().getFormat("0.00"));
        styles.put("formula_2", style);

        return styles;
    }

	
	
	
	
	public void resetFiscalYearRpt()
	{
		this.fiscalYearRpt=new FiscalRpt();
		this.selectedFiscalYear=null;
	}

	public String getSelectedFiscalYear() {
		return selectedFiscalYear;
	}

	public void setSelectedFiscalYear(String selectedFiscalYear) {
		this.selectedFiscalYear = selectedFiscalYear;
	}
	
	public BigDecimal getQ1AllIndxTotal() {
		return q1AllIndxTotal;
	}


	public void setQ1AllIndxTotal(BigDecimal q1AllIndxTotal) {
		this.q1AllIndxTotal = q1AllIndxTotal;
	}


	public BigDecimal getQ2AllIndxTotal() {
		return q2AllIndxTotal;
	}


	public void setQ2AllIndxTotal(BigDecimal q2AllIndxTotal) {
		this.q2AllIndxTotal = q2AllIndxTotal;
	}


	public BigDecimal getQ3AllIndxTotal() {
		return q3AllIndxTotal;
	}


	public void setQ3AllIndxTotal(BigDecimal q3AllIndxTotal) {
		this.q3AllIndxTotal = q3AllIndxTotal;
	}


	public BigDecimal getQ4AllIndxTotal() {
		return q4AllIndxTotal;
	}


	public void setQ4AllIndxTotal(BigDecimal q4AllIndxTotal) {
		this.q4AllIndxTotal = q4AllIndxTotal;
	}


	public BigDecimal getAllIndxTotalsTotal() {
		return allIndxTotalsTotal;
	}


	public void setAllIndxTotalsTotal(BigDecimal allIndxTotalsTotal) {
		this.allIndxTotalsTotal = allIndxTotalsTotal;
	}
}
