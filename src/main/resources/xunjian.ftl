<?xml version="1.0"?>
<?mso-application progid="Excel.Sheet"?>
<Workbook xmlns="urn:schemas-microsoft-com:office:spreadsheet"
 xmlns:o="urn:schemas-microsoft-com:office:office"
 xmlns:x="urn:schemas-microsoft-com:office:excel"
 xmlns:ss="urn:schemas-microsoft-com:office:spreadsheet"
 xmlns:html="http://www.w3.org/TR/REC-html40">
 <DocumentProperties xmlns="urn:schemas-microsoft-com:office:office">
  <Author>吴航</Author>
  <LastAuthor>吴航</LastAuthor>
  <Created>2020-10-26T16:00:58Z</Created>
  <LastSaved>2020-10-28T12:19:06Z</LastSaved>
  <Version>16.00</Version>
 </DocumentProperties>
 <OfficeDocumentSettings xmlns="urn:schemas-microsoft-com:office:office">
  <AllowPNG/>
 </OfficeDocumentSettings>
 <ExcelWorkbook xmlns="urn:schemas-microsoft-com:office:excel">
  <WindowHeight>16940</WindowHeight>
  <WindowWidth>27840</WindowWidth>
  <WindowTopX>5440</WindowTopX>
  <WindowTopY>2540</WindowTopY>
  <ProtectStructure>False</ProtectStructure>
  <ProtectWindows>False</ProtectWindows>
 </ExcelWorkbook>
 <Styles>
  <Style ss:ID="Default" ss:Name="Normal">
   <Alignment ss:Vertical="Center"/>
   <Borders/>
   <Font ss:FontName="等线" x:CharSet="134" ss:Size="12" ss:Color="#000000"/>
   <Interior/>
   <NumberFormat/>
   <Protection/>
  </Style>
  <Style ss:ID="s62">
   <Alignment ss:Horizontal="Center" ss:Vertical="Center" ss:WrapText="1"/>
   <Borders>
    <Border ss:Position="Bottom" ss:LineStyle="Continuous" ss:Weight="2"
     ss:Color="#000000"/>
    <Border ss:Position="Left" ss:LineStyle="Continuous" ss:Weight="2"
     ss:Color="#000000"/>
    <Border ss:Position="Right" ss:LineStyle="Continuous" ss:Weight="2"
     ss:Color="#000000"/>
    <Border ss:Position="Top" ss:LineStyle="Continuous" ss:Weight="2"
     ss:Color="#000000"/>
   </Borders>
   <Font ss:FontName="微软雅黑" x:CharSet="134" ss:Color="#FFFFFF" ss:Bold="1"/>
   <Interior ss:Color="#4F81BD" ss:Pattern="Solid"/>
  </Style>
  <Style ss:ID="s63">
   <NumberFormat ss:Format="Percent"/>
  </Style>
  <Style ss:ID="s66">
   <Alignment ss:Horizontal="Center" ss:Vertical="Center"/>
   <Borders>
    <Border ss:Position="Top" ss:LineStyle="Continuous" ss:Weight="2"
     ss:Color="#000000"/>
   </Borders>
  </Style>
 </Styles>
 <Worksheet ss:Name="每日巡检指标">
  <Table ss:ExpandedColumnCount="12" ss:ExpandedRowCount="${allCount}" x:FullColumns="1"
   x:FullRows="1" ss:DefaultColumnWidth="65" ss:DefaultRowHeight="16">
   <Column ss:AutoFitWidth="0" ss:Width="82"/>
   <Column ss:AutoFitWidth="0" ss:Width="83"/>
   <Row ss:AutoFitHeight="0" ss:Height="35">
    <Cell ss:StyleID="s62"><Data ss:Type="String">交易码&#10;（接口名称）</Data></Cell>
    <Cell ss:StyleID="s62"><Data ss:Type="String">交易码中文名称</Data></Cell>
    <Cell ss:StyleID="s62"><Data ss:Type="String">交易量</Data></Cell>
    <Cell ss:StyleID="s62"><Data ss:Type="String">响应时间</Data></Cell>
    <Cell ss:StyleID="s62"><Data ss:Type="String">响应率</Data></Cell>
    <Cell ss:StyleID="s62"><Data ss:Type="String">成功率</Data></Cell>
    <Cell ss:StyleID="s62"><Data ss:Type="String">承兑率</Data></Cell>
    <Cell ss:StyleID="s62"><Data ss:Type="String">返回码&#10;（错误码）</Data></Cell>
    <Cell ss:StyleID="s62"><Data ss:Type="String">交易量</Data></Cell>
    <Cell ss:StyleID="s62"><Data ss:Type="String">占比</Data></Cell>
    <Cell ss:StyleID="s62"><Data ss:Type="String">响应时间</Data></Cell>
    <Cell ss:StyleID="s62"><Data ss:Type="String">错误码描述</Data></Cell>
   </Row>
   <#list record as record>
    <Row ss:AutoFitHeight="0">
      <Cell ss:MergeDown="${record.failSize}" ss:StyleID="s66"><Data ss:Type="String">${record.trxCode}</Data></Cell>
      <Cell ss:MergeDown="${record.failSize}" ss:StyleID="s66"><Data ss:Type="String">${record.trxCodeCN}</Data></Cell>
      <Cell ss:MergeDown="${record.failSize}" ss:StyleID="s66"><Data ss:Type="String">${record.tranCount}</Data></Cell>
      <Cell ss:MergeDown="${record.failSize}" ss:StyleID="s66"><Data ss:Type="String">${record.ansTime}</Data></Cell>
      <Cell ss:MergeDown="${record.failSize}" ss:StyleID="s66"><Data ss:Type="String">${record.ansRet}</Data></Cell>
      <Cell ss:MergeDown="${record.failSize}" ss:StyleID="s66"><Data ss:Type="String">${record.sucRet}</Data></Cell>
      <Cell ss:MergeDown="${record.failSize}" ss:StyleID="s66"><Data ss:Type="String">${record.acceptRet}</Data></Cell>
      <Cell ss:StyleID="s63"><Data ss:Type="String">${record.sucCode}</Data></Cell>
      <Cell ss:StyleID="s63"><Data ss:Type="String">${record.sucCount}</Data></Cell>
      <Cell ss:StyleID="s63"><Data ss:Type="String">${record.sucPresent}</Data></Cell>
      <Cell ss:StyleID="s63"><Data ss:Type="String">${record.sucTime}</Data></Cell>
      <Cell ss:StyleID="s63"><Data ss:Type="String">${record.sucDesc}</Data></Cell>
    </Row>
    <#list record.fail as fail>
      <Row ss:AutoFitHeight="0">
        <Cell ss:Index="8" ss:StyleID="s63"><Data ss:Type="String">${fail.failCode}</Data></Cell>
        <Cell ss:StyleID="s63"><Data ss:Type="String">${fail.failCount}</Data></Cell>
        <Cell ss:StyleID="s63"><Data ss:Type="String">${fail.failPresent}</Data></Cell>
        <Cell ss:StyleID="s63"><Data ss:Type="String">${fail.failTime}</Data></Cell>
        <Cell ss:StyleID="s63"><Data ss:Type="String">${fail.failDesc}</Data></Cell>
      </Row>
    </#list>
   </#list>
  </Table>
  <WorksheetOptions xmlns="urn:schemas-microsoft-com:office:excel">
   <PageSetup>
    <Header x:Margin="0.3"/>
    <Footer x:Margin="0.3"/>
    <PageMargins x:Bottom="0.75" x:Left="0.7" x:Right="0.7" x:Top="0.75"/>
   </PageSetup>
   <Unsynced/>
   <Zoom>150</Zoom>
   <Selected/>
   <Panes>
    <Pane>
     <Number>3</Number>
     <ActiveRow>6</ActiveRow>
     <ActiveCol>5</ActiveCol>
    </Pane>
   </Panes>
   <ProtectObjects>False</ProtectObjects>
   <ProtectScenarios>False</ProtectScenarios>
  </WorksheetOptions>
 </Worksheet>
 <Worksheet ss:Name="每周业务量指标">
  <Table ss:ExpandedColumnCount="1" ss:ExpandedRowCount="1" x:FullColumns="1"
   x:FullRows="1" ss:DefaultColumnWidth="65" ss:DefaultRowHeight="16">
   <Row ss:AutoFitHeight="0"/>
  </Table>
  <WorksheetOptions xmlns="urn:schemas-microsoft-com:office:excel">
   <PageSetup>
    <Header x:Margin="0.3"/>
    <Footer x:Margin="0.3"/>
    <PageMargins x:Bottom="0.75" x:Left="0.7" x:Right="0.7" x:Top="0.75"/>
   </PageSetup>
   <Unsynced/>
   <ProtectObjects>False</ProtectObjects>
   <ProtectScenarios>False</ProtectScenarios>
  </WorksheetOptions>
 </Worksheet>
 <Worksheet ss:Name="每日服务器指标">
  <Table ss:ExpandedColumnCount="1" ss:ExpandedRowCount="1" x:FullColumns="1"
   x:FullRows="1" ss:DefaultColumnWidth="65" ss:DefaultRowHeight="16">
   <Row ss:AutoFitHeight="0"/>
  </Table>
  <WorksheetOptions xmlns="urn:schemas-microsoft-com:office:excel">
   <PageSetup>
    <Header x:Margin="0.3"/>
    <Footer x:Margin="0.3"/>
    <PageMargins x:Bottom="0.75" x:Left="0.7" x:Right="0.7" x:Top="0.75"/>
   </PageSetup>
   <Unsynced/>
   <ProtectObjects>False</ProtectObjects>
   <ProtectScenarios>False</ProtectScenarios>
  </WorksheetOptions>
 </Worksheet>
</Workbook>
