import React from 'react';
import { Table, TableBody, TableCell, TableContainer, TableHead, TableRow, Paper, colors } from '@mui/material';
import excelImage from "../../resources/excel.png"
import {tableBGColor, tableHeaderColor, tableRowEvenColor, tableRowOddColor, tableTextColor } from '../colors';

const CourseTable = ({ data, onRowClick, activeTab, downloadable, colors, numberOfTabs }) => {
  var maxHeight = 600; 
  const rowHeight = 60; 
  const headRowHeight = 70;

  // Set maximum height to be the same as the tabs side bar height
  if (((numberOfTabs * rowHeight) + headRowHeight) > maxHeight) {
    maxHeight = (numberOfTabs * rowHeight) + headRowHeight
  }

  // filter courses
  if (activeTab == "nofilter" || activeTab == '') {
    var filteredData = data
  } else {
    var filteredData = data.filter(course => course.creditsType === activeTab);
  }


  const shouldRenderEmptySpace = filteredData.length * rowHeight + headRowHeight< maxHeight;

  const headerColor = colors.header
  const rowColor = colors.row

  // Function for creating CSV data
  const convertToCSV = (data) => {
    const header = ['Course Name', 'Course Number', 'Credits Number'];
    const rows = data.map(row => ['"' + row.courseName + '"', '"' + row.courseNumber + '"', '"' + row.creditsNumber + '"']);

    const csvContent = [
      header.join(','),
      ...rows.map(row => row.join(','))
    ].join('\n');

    return csvContent;
  };

  // Function for downloading the CSV file
  const downloadCSV = (csvContent, filename) => {
    const blob = new Blob([csvContent], { type: 'text/csv' });
    const url = URL.createObjectURL(blob);
    const a = document.createElement('a');
    a.href = url;
    a.download = filename;
    a.click();
    URL.revokeObjectURL(url);
  };

  // Handles the download csv
  const handleDownloadCSV = () => {
    const csvContent = convertToCSV(filteredData);
    const filename = 'study_plan.csv';
    downloadCSV(csvContent, filename);
  };


  return (
    <div>
    <Paper style={{ maxHeight: `${maxHeight}px`, overflowY: 'auto' , direction: 'rtl', borderRadius: '0px' }}>
      <Table aria-label="Beautiful Table" style={{ tableLayout: 'fixed', width: '100%' }}>
        <TableHead>
          <TableRow style={{position: 'sticky', top: 0, zIndex: 1, height: `${headRowHeight}px`, background: headerColor}}>
            <TableCell style={{ color: tableTextColor, fontWeight: 'bold' , textAlign: 'right', width: '60%'  }}>קורס</TableCell>
            <TableCell style={{ color: tableTextColor, fontWeight: 'bold' , textAlign: 'right', width: '20%'   }}>מספר קורס</TableCell>
            <TableCell style={{ color: tableTextColor, fontWeight: 'bold' , textAlign: 'right', width: '20%'   }}>נק"ז</TableCell>
          </TableRow>
        </TableHead>
        <TableBody>
          {filteredData.map((row, index) => (
            <TableRow key={index} onClick={() => onRowClick(row)} style={{height: `${rowHeight}px`, background: index % 2 === 0 ? rowColor : tableRowOddColor}}>
              <TableCell style={{ textAlign: 'right', wordWrap: 'break-word', width: '60%' , color: tableTextColor}}>{row.courseName}</TableCell>
              <TableCell style={{ textAlign: 'right', wordWrap: 'break-word', width: '20%', color: tableTextColor }}>{row.courseNumber}</TableCell>
              <TableCell style={{ textAlign: 'right', wordWrap: 'break-word', width: '20%', color: tableTextColor }}>{row.creditsNumber}</TableCell>
            </TableRow>
          ))}
        </TableBody>
      </Table>
      {/* Conditionally render empty space only if needed */}
      {shouldRenderEmptySpace && (
        <div style={{ height: `${maxHeight - headRowHeight - (filteredData.length * rowHeight)}px` }} />
      )}

    </Paper>
    {downloadable && (
      <div style={{ display: 'flex', justifyContent: 'center', padding: '10px' , height: '100px', direction: 'rtl'}}>
        <p style={{color: 'green', fontSize: '30px', marginTop: '15px', marginLeft: '10px'}}>להורדה</p>
        <img
          src={excelImage}
          alt="Download CSV"
          style={{ cursor: 'pointer' }}
          onClick={handleDownloadCSV}
        />
      </div>
    )}
    </div>
  );
}

export default CourseTable;
