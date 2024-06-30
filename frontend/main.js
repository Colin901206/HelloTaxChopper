// Function to hide the load window
function hideLoadWindow() {
    document.getElementById('load-overlay').style.display = 'none';
}

// Event listener for the load form submission
document.getElementById('load-form').addEventListener('submit', function(e) {
    e.preventDefault();
    const pre_tax_code = document.getElementById('pre_tax_code').value;
    
    // Here you would typically send these credentials to a server for verification
    // For this example, we'll just log them and hide the load window
    hideLoadWindow();
    loadForm(pre_tax_code);
});

// Event listener for the close button and new tax button
document.getElementById('close-load').addEventListener('click', hideLoadWindow);

function addRow(tableId) {
    const table = document.getElementById(tableId);
    const newRow = table.insertRow(-1);
    const lastRow = table.rows[table.rows.length - 2];
    const cellCount = lastRow.cells.length;

    for (let i = 0; i < cellCount; i++) {
        const newCell = newRow.insertCell(i);
        if (i === 0) {
            // Line number column
            newCell.textContent = table.rows.length - 1;
            newCell.className = 'line-number';
        } else if (i === cellCount - 1) {
            // Action column
            newCell.innerHTML = '<button onclick="removeRow(this)">-</button>';
            newCell.className = 'action-column';
        } else {
            const content = lastRow.cells[i].innerHTML;
            newCell.innerHTML = content;
        }
    }

    // Update names and IDs of new inputs
    const inputs = newRow.querySelectorAll('input, select');
    inputs.forEach(input => {
        const name = input.getAttribute('name');
        if (name) {
            const newName = name.replace(/\d+$/, match => parseInt(match) + 1);
            input.setAttribute('name', newName);
            input.value = ''; // Clear the value for the new row
        }
    });

    // If the added row is in Part 3 table, update the total
    if (tableId === 'part3Table') {
        updatePart3Total();
    }
}

function removeRow(button) {
    const row = button.parentNode.parentNode;
    const table = row.parentNode;
    if (table.rows.length > 2) {
        table.deleteRow(row.rowIndex);
        updateLineNumbers(table);
        
        // If the removed row is from Part 3 table, update the total
        if (table.id === 'part3Table') {
            updatePart3Total();
        }
    } else {
        alert("Cannot remove the last row.");
    }
}

function updateLineNumbers(table) {
    const rows = table.rows;
    for (let i = 1; i < rows.length; i++) {
        rows[i].cells[0].textContent = i;
    }
}

function clearCalculatedFields() {
    const fieldsToEmpty = [
        'part2_total_credits', 'part3_total_inv', 'part4_input3', 'part4_input4', 'part4_input6', 
        'part4_input11', 'part4_input12', 'part4_input13', 'part4_input14', 'part4_input15', 
        'part4_input16', 'part4_input17', 'part4_input18', 'part4_input19', 'part4_input20', 
        'part4_input21', 'part4_input23'
    ];

    fieldsToEmpty.forEach(id => {
        const element = document.getElementById(id);
        if (element) {
            element.value = '';
        }
    });
}

function updatePart2Calculations() {
    const credit901 = parseFloat(document.getElementById('credit_901').value) || 0;
    const credit902 = parseFloat(document.getElementById('credit_902').value) || 0;
    const credit903 = parseFloat(document.getElementById('credit_903').value) || 0;
    const total = credit901 + credit902 + credit903;
    
    const totalElement = document.getElementById('part2_total_credits');
    if (totalElement) {
        totalElement.value = total > 0 ? total.toFixed(2) : '';
    }
}

function updatePart3Total() {
    const table = document.getElementById('part3Table');
    let total = 0;

    // Sum up all the "Amount of investment" values
    for (let i = 1; i < table.rows.length; i++) {
        const amountInput = table.rows[i].querySelector('input[name^="part3_inv_amount"]');
        if (amountInput) {
            total += parseFloat(amountInput.value) || 0;
        }
    }

    // Update the total in the 'part3_total_inv' field
    const totalElement = document.getElementById('part3_total_inv');
    if (totalElement) {
        totalElement.value = total > 0 ? total.toFixed(2) : '';
    }
}

function updatePart4Calculations() {
    // Helper function to get numeric value from input
    const getValue = (id) => {
        const element = document.getElementById(id);
        return element ? (parseFloat(element.value) || 0) : 0;
    };

    // Helper function to set value to input
    const setValue = (id, value) => {
        const element = document.getElementById(id);
        if (element) {
            element.value = value > 0 ? value.toFixed(2) : '';
        }
    };

    // Calculations
    const input1 = getValue('part4_input1');
    const input2 = getValue('part4_input2');
    const sum = input1 + input2;
    setValue('part4_input4', sum);
    
    const input3Element = document.getElementById('part4_input3');
    if (input3Element) {
        input3Element.value = sum > 0 ? `${input1.toFixed(2)} + ${input2.toFixed(2)}` : '';
    }

    const input4 = getValue('part4_input4');
    const input5 = getValue('part4_input5');
    const input6 = input4 - input5;
    setValue('part4_input6', input6);

    const input7 = getValue('part4_input7');
    const input8 = getValue('part4_input8');
    const input9 = getValue('part4_input9');
    const input10 = getValue('part4_input10');
    const input11 = input7 + input8 + input9 + input10;
    setValue('part4_input11', input11);

    const input12 = input11 * 0.3833333;
    setValue('part4_input12', input12);

    const input13 = Math.min(input2, input6);
    setValue('part4_input13', input13);

    const input14 = input13 / 0.3833333;
    setValue('part4_input14', input14);

    const input15 = Math.min(input14, input11);
    setValue('part4_input15', input15);

    const input16 = input11 - input15;
    setValue('part4_input16', input16);

    const input17 = input15;
    setValue('part4_input17', input17);

    const input18 = input17 * 0.3833333;
    setValue('part4_input18', input18);

    const input19 = input16;
    setValue('part4_input19', input19);

    const input20 = input19 * 0.3833333;
    setValue('part4_input20', input20);

    const input21 = input18 + input20;
    setValue('part4_input21', input21);

    const input22 = getValue('part4_input22');
    const input23 = Math.max(0, input6 - input22);
    setValue('part4_input23', input23);
}

// Add event listeners
document.addEventListener('DOMContentLoaded', function() {

    // Add event listener for load button
    const headerLoadBtn = document.getElementById('header-load-btn');
    if (headerLoadBtn) {
        headerLoadBtn.addEventListener('click', function() {
            document.getElementById('load-overlay').style.display = 'flex';
        });
    }

    // Clear calculated fields on page load
    clearCalculatedFields();

    // Part 1 - Add row
    const part1Table = document.getElementById('part1Table');
    if (part1Table) {
        const addButton = part1Table.querySelector('th button');
        if (addButton) {
            addButton.addEventListener('click', () => addRow('part1Table'));
        }
    }

    // Part 2 calculations
    const credit901 = document.getElementById('credit_901');
    const credit902 = document.getElementById('credit_902');
    const credit903 = document.getElementById('credit_903');

    if (credit901) credit901.addEventListener('input', updatePart2Calculations);
    if (credit902) credit902.addEventListener('input', updatePart2Calculations);
    if (credit903) credit903.addEventListener('input', updatePart2Calculations);
    
    // Part 3 calculations and add row
    const part3Table = document.getElementById('part3Table');
    if (part3Table) {
        part3Table.addEventListener('input', function(event) {
            if (event.target.name && event.target.name.startsWith('part3_inv_amount')) {
                updatePart3Total();
            }
        });
        const addButton = part3Table.querySelector('th button');
        if (addButton) {
            addButton.addEventListener('click', () => {
                addRow('part3Table');
                updatePart3Total();
            });
        }
    }

    // Part 4 calculations
    const part4InputsToWatch = [
        'part4_input1', 'part4_input2', 'part4_input5',
        'part4_input7', 'part4_input8', 'part4_input9', 'part4_input10',
        'part4_input22'
    ];
    part4InputsToWatch.forEach(id => {
        const element = document.getElementById(id);
        if (element) {
            element.addEventListener('input', updatePart4Calculations);
        }
    });

    // Radio button for tax year
    const taxYearRadios = document.querySelectorAll('input[name="part3_tax_year_after_2023"]');
    taxYearRadios.forEach(radio => {
        radio.addEventListener('change', updatePart4Calculations);
    });

    // Add event listeners for save buttons
    const bottomSaveBtn = document.getElementById('bottom-save-btn');

    if (bottomSaveBtn) bottomSaveBtn.addEventListener('click', saveForm);

    // Initial calculations
    if (typeof updatePart2Calculations === 'function') updatePart2Calculations();
    if (typeof updatePart3Total === 'function') updatePart3Total();
    if (typeof updatePart4Calculations === 'function') updatePart4Calculations();
});

// Helper function for validation
function validateNotEmpty(value, fieldName) {
    if (value === null || value === undefined || value === '') {
        return `${fieldName} cannot be empty.`;
    }
    
    if (typeof value === 'string') {
        if (value.trim() === '') {
            return `${fieldName} cannot be empty.`;
        }
    } else if (typeof value === 'number') {
        if (isNaN(value)) {
            return `${fieldName} must be a valid number.`;
        }
    }
    
    return null;
}

// Part 1 Data Collection with validation
function getPart1Data() {
    const part1Table = document.getElementById('part1Table');
    if (!part1Table) {
        console.error('Part 1 Table not found');
        return { data: null, errors: ['Part 1 Table not found'] };
    }

    const part1Data = [];
    const errors = [];
    const rows = part1Table.querySelectorAll('tr:not(:first-child)'); // Skip the header row

    rows.forEach((row, index) => {
        const inputs = row.querySelectorAll('input, select');
        const rowData = {
            part1TableLine: index + 1,
            payerCorporation: inputs[0].value,
            isConnected: inputs[1].value,
            businessNumber: inputs[2].value
        };

        // Validate fields
        const payerCorpError = validateNotEmpty(rowData.payerCorporation, `Payer Corporation Name (Row ${index + 1})`);
        const isConnectedError = validateNotEmpty(rowData.isConnected, `Is Corporation Connected (Row ${index + 1})`);
        const businessNumberError = validateNotEmpty(rowData.businessNumber, `Business Number (Row ${index + 1})`);

        if (payerCorpError) errors.push(payerCorpError);
        if (isConnectedError) errors.push(isConnectedError);
        if (businessNumberError) errors.push(businessNumberError);

        part1Data.push(rowData);
    });

    return { data: part1Data, errors };
}

// Part 2 Data Collection with validation
function getPart2Data() {
    const getValue = (name) => {
        const element = document.querySelector(`input[name="${name}"]`);
        return element ? element.value : '';
    };

    const data = {
        firstPreviousTaxYear: getValue('first_pre_tax_year'),
        firstPreviousTaxMonth: getValue('first_pre_tax_month'),
        firstPreviousTaxDay: getValue('first_pre_tax_day'),
        firstPreviousTaxCredit: document.getElementById('credit_901').value,
        secondPreviousTaxYear: getValue('second_pre_tax_year'),
        secondPreviousTaxMonth: getValue('second_pre_tax_month'),
        secondPreviousTaxDay: getValue('second_pre_tax_day'),
        secondPreviousTaxCredit: document.getElementById('credit_902').value,
        thirdPreviousTaxYear: getValue('third_pre_tax_year'),
        thirdPreviousTaxMonth: getValue('third_pre_tax_month'),
        thirdPreviousTaxDay: getValue('third_pre_tax_day'),
        thirdPreviousTaxCredit: document.getElementById('credit_903').value,
        totalCredits: document.getElementById('part2_total_credits').value
    };

    const errors = [];
    
    // Validate fields
    Object.entries(data).forEach(([key, value]) => {
        if (key !== 'totalCredits') {  // Skip validation for totalCredits as it's calculated
            const error = validateNotEmpty(value, key);
            if (error) errors.push(error);
        }
    });

    return { data, errors };
}

// Part 3 Data Collection with validation
function getPart3Data() {
    const part3Table = document.getElementById('part3Table');
    const part3BottomTable = document.getElementById('part3TableButtom');
    if (!part3Table || !part3BottomTable) {
        console.error('Part 3 Table or Bottom Table not found');
        return { data: null, errors: ['Part 3 Table or Bottom Table not found'] };
    }

    const part3Data = [];
    const errors = [];
    const rows = part3Table.querySelectorAll('tr:not(:first-child)'); // Skip only the header row
    let lineNumber = 1; // Initialize line number counter

    rows.forEach((row) => {
        const inputs = row.querySelectorAll('input');
        if (inputs.length === 5) { // Regular row
            const rowData = {
                part3TableLine: lineNumber,
                capitalCostAllowanceClassNumber: inputs[0].value,
                descriptionOfInvestment: inputs[1].value,
                dateAvailableForUse: inputs[2].value,
                locationUsedInAtlantic: inputs[3].value,
                amountOfInvestment: inputs[4].value
            };

            // Validate fields
            Object.entries(rowData).forEach(([key, value]) => {
                if (key !== 'part3TableLine') {
                    const error = validateNotEmpty(value, `${key} (Row ${lineNumber})`);
                    if (error) errors.push(error);
                }
            });

            part3Data.push(rowData);
            lineNumber++; // Increment line number for the next row
        }
    });

    const totalInvestmentsInput = part3BottomTable.querySelector('input[name="part3_total_inv"]');
    const totalInvestments = totalInvestmentsInput ? totalInvestmentsInput.value : '';

    const taxYearAfter2023 = document.querySelector('input[name="part3_tax_year_after_2023"]:checked');
    if (!taxYearAfter2023) {
        errors.push('Tax year after 2023 must be selected');
    }

    const data = {
        investments: part3Data,
        totalInvestments: totalInvestments,
        taxYearAfter2023: taxYearAfter2023 ? taxYearAfter2023.value : ''
    };

    return { data, errors };
}

// Part 4 Data Collection with validation
function getPart4Data() {
    const getValue = (id) => {
        const element = document.getElementById(id);
        return element ? element.value : '';
    };

    const data = {
        dividendsBefore2024: getValue('part4_input1'),
        dividendsAfter2023: getValue('part4_input2'),
        partIVTaxBeforeDeductions: getValue('part4_input3'),
        partIVTaxBeforeDeductionsTotal: getValue('part4_input4'),
        partIVITaxPayable: getValue('part4_input5'),
        subtotalM: getValue('part4_input6'),
        currentYearNonCapitalLoss: getValue('part4_input7'),
        previousYearsNonCapitalLoss: getValue('part4_input8'),
        currentYearFarmLoss: getValue('part4_input9'),
        previousYearsFarmLoss: getValue('part4_input10'),
        totalLossesApplied: getValue('part4_input11'),
        amountGMultiplied: getValue('part4_input12'),
        amountBOrMWhicheverLess: getValue('part4_input13'),
        amountBOrMDivided: getValue('part4_input14'),
        amount1OrGWhicheverLess: getValue('part4_input15'),
        amountGMinusAmount2: getValue('part4_input16'),
        amount2: getValue('part4_input17'),
        amount2Multiplied: getValue('part4_input18'),
        amount3: getValue('part4_input19'),
        amount3Multiplied: getValue('part4_input20'),
        subtotalAmountIPlusJ: getValue('part4_input21'),
        amountHOrK: getValue('part4_input22'),
        partIVTaxPayable: getValue('part4_input23'),
        propertyType: document.querySelector('input[name="part4_property_type"]:checked') ? 
                      document.querySelector('input[name="part4_property_type"]:checked').value : ''
    };

    const errors = [];
    const requiredFields = ['part4_input1', 'part4_input2', 'part4_input5', 'part4_input7', 
                            'part4_input8', 'part4_input9', 'part4_input10', 'part4_input22'];
    
    requiredFields.forEach(field => {
        const error = validateNotEmpty(getValue(field), field);
        if (error) errors.push(error);
    });

    if (!data.propertyType) {
        errors.push('Property type must be selected');
    }

    return { data, errors };
}

// Section B Data Collection with validation
function getSectionBData() {
    const data = {
        uncertainties: document.getElementById('sectionb_uncertainties').value,
        advancements: document.getElementById('sectionb_advancements').value
    };

    const errors = [];
    Object.entries(data).forEach(([key, value]) => {
        const error = validateNotEmpty(value, key);
        if (error) errors.push(error);
    });

    return { data, errors };
}

// Combine all parts data
function getFormData() {
    const part1 = getPart1Data();
    const part2 = getPart2Data();
    const part3 = getPart3Data();
    const part4 = getPart4Data();
    const sectionB = getSectionBData();

    const allErrors = [
        ...part1.errors,
        ...part2.errors,
        ...part3.errors,
        ...part4.errors,
        ...sectionB.errors
    ];

    return {
        isValid: allErrors.length === 0,
        errors: allErrors,
        data: {
            taxCode: document.getElementById('tax_code').value,
            part1: part1.data,
            part2: part2.data,
            part3: part3.data,
            part4: part4.data,
            sectionB: sectionB.data
        }
    };
}

function saveForm() {
    const formData = getFormData();
    if (!formData.isValid) {
        console.log('Form has validation errors:');
        console.log(formData.errors);
        alert('Please fill in all required fields before saving.');
        return;
    }

    const jsonFormData = JSON.stringify(formData.data, null, 2);
    console.log('Form Data:');
    console.log(jsonFormData);

    // Make API call to save the data
    fetch('/taxserver/tax/save', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: jsonFormData,
    })
    .then(response => response.json())
    .then(data => {
        if (data.success && data.status === 200) {
            alert(`Form saved successfully! <br/ > Your tax code is: ${data.data}`);
        } else {
            alert('Failed to save the form. Please try again.');
        }
    })
    .catch((error) => {
        console.error('Error:', error);
        alert('An error occurred while saving the form. Please try again.');
    });
}


function loadForm(preTaxCode) {
    console.log('load attempt:', preTaxCode);

    if (!preTaxCode) {
        alert('Please enter a tax code.');
        return;
    }

    fetch(`/taxserver/tax/info?taxCode=${preTaxCode}`, {
        method: 'GET',
        headers: {
            'Content-Type': 'application/json',
        },
    })
    .then(response => response.json())
    .then(data => {
        if (data.success && data.status === 200) {
            console.log('Load successful. Tax data:', data.data);
            populateForm(data.data);
            hideLoadWindow();
        } else {
            console.error('Load failed:', data.message);
            alert('Failed to load the form data. Please try again.');
        }
    })
    .catch((error) => {
        console.error('Error:', error);
        alert('An error occurred while loading the form data. Please try again.');
    });
}

function populateForm(data) {
    document.getElementById('tax_code').value = data.taxCode;
    // Populate Part 1
    const part1Table = document.getElementById('part1Table');
    const part1Rows = part1Table.querySelectorAll('tr:not(:first-child)');
    data.part1.forEach((item, index) => {
        if (index >= part1Rows.length - 1) {
            addRow('part1Table');
        }
        const row = part1Table.rows[index + 1];
        row.cells[1].querySelector('input').value = item.payerCorporation;
        row.cells[2].querySelector('select').value = item.isConnected;
        row.cells[3].querySelector('input').value = item.businessNumber;
    });

    // Populate Part 2
    document.querySelector('input[name="first_pre_tax_year"]').value = data.part2.firstPreviousTaxYear;
    document.querySelector('input[name="first_pre_tax_month"]').value = data.part2.firstPreviousTaxMonth;
    document.querySelector('input[name="first_pre_tax_day"]').value = data.part2.firstPreviousTaxDay;
    document.getElementById('credit_901').value = data.part2.firstPreviousTaxCredit;
    document.querySelector('input[name="second_pre_tax_year"]').value = data.part2.secondPreviousTaxYear;
    document.querySelector('input[name="second_pre_tax_month"]').value = data.part2.secondPreviousTaxMonth;
    document.querySelector('input[name="second_pre_tax_day"]').value = data.part2.secondPreviousTaxDay;
    document.getElementById('credit_902').value = data.part2.secondPreviousTaxCredit;
    document.querySelector('input[name="third_pre_tax_year"]').value = data.part2.thirdPreviousTaxYear;
    document.querySelector('input[name="third_pre_tax_month"]').value = data.part2.thirdPreviousTaxMonth;
    document.querySelector('input[name="third_pre_tax_day"]').value = data.part2.thirdPreviousTaxDay;
    document.getElementById('credit_903').value = data.part2.thirdPreviousTaxCredit;
    document.getElementById('part2_total_credits').value = data.part2.totalCredits;

    // Populate Part 3
    const part3Table = document.getElementById('part3Table');
    const part3Rows = part3Table.querySelectorAll('tr:not(:first-child)');
    data.part3.investments.forEach((item, index) => {
        if (index >= part3Rows.length - 1) {
            addRow('part3Table');
        }
        const row = part3Table.rows[index + 1];
        row.cells[1].querySelector('input').value = item.capitalCostAllowanceClassNumber;
        row.cells[2].querySelector('input').value = item.descriptionOfInvestment;
        row.cells[3].querySelector('input').value = item.dateAvailableForUse;
        row.cells[4].querySelector('input').value = item.locationUsedInAtlantic;
        row.cells[5].querySelector('input').value = item.amountOfInvestment;
    });
    document.getElementById('part3_total_inv').value = data.part3.totalInvestments;
    document.querySelector(`input[name="part3_tax_year_after_2023"][value="${data.part3.taxYearAfter2023}"]`).checked = true;

    // Populate Part 4
    document.getElementById('part4_input1').value = data.part4.dividendsBefore2024;
    document.getElementById('part4_input2').value = data.part4.dividendsAfter2023;
    document.getElementById('part4_input3').value = data.part4.partIVTaxBeforeDeductions;
    document.getElementById('part4_input4').value = data.part4.partIVTaxBeforeDeductionsTotal;
    document.getElementById('part4_input5').value = data.part4.partIVITaxPayable;
    document.getElementById('part4_input6').value = data.part4.subtotalM;
    document.getElementById('part4_input7').value = data.part4.currentYearNonCapitalLoss;
    document.getElementById('part4_input8').value = data.part4.previousYearsNonCapitalLoss;
    document.getElementById('part4_input9').value = data.part4.currentYearFarmLoss;
    document.getElementById('part4_input10').value = data.part4.previousYearsFarmLoss;
    document.getElementById('part4_input11').value = data.part4.totalLossesApplied;
    document.getElementById('part4_input12').value = data.part4.amountGMultiplied;
    document.getElementById('part4_input13').value = data.part4.amountBOrMWhicheverLess;
    document.getElementById('part4_input14').value = data.part4.amountBOrMDivided;
    document.getElementById('part4_input15').value = data.part4.amount1OrGWhicheverLess;
    document.getElementById('part4_input16').value = data.part4.amountGMinusAmount2;
    document.getElementById('part4_input17').value = data.part4.amount2;
    document.getElementById('part4_input18').value = data.part4.amount2Multiplied;
    document.getElementById('part4_input19').value = data.part4.amount3;
    document.getElementById('part4_input20').value = data.part4.amount3Multiplied;
    document.getElementById('part4_input21').value = data.part4.subtotalAmountIPlusJ;
    document.getElementById('part4_input22').value = data.part4.amountHOrK;
    document.getElementById('part4_input23').value = data.part4.partIVTaxPayable;
    document.querySelector(`input[name="part4_property_type"][value="${data.part4.propertyType}"]`).checked = true;

    // Populate Section B
    document.getElementById('sectionb_uncertainties').value = data.sectionB.uncertainties;
    document.getElementById('sectionb_advancements').value = data.sectionB.advancements;

    // Trigger calculations
    updatePart2Calculations();
    updatePart3Total();
    updatePart4Calculations();
}