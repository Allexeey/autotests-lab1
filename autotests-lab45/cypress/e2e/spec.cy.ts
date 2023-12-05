describe('Запуск приложения', () => {
  beforeEach(() => {
    cy.visit('http://localhost:4200/');
  });

  it('Существует первое окно ввода', () => {
    cy.get('#input1').should('be.visible');
  });

  it('Существует второе окно ввода', () => {
    cy.get('#input2').should('be.visible');
  });

  it('Существует выпадающий список систем счисления', () => {
    cy.get('#system').should('be.visible');
  });

  it('Существует выпадающий список операций', () => {
    cy.get('#operationSelector').should('be.visible');
  });

  it('Существует кнопка расчёта', () => {
    cy.get('#calcButton').should('be.visible');
  });

  it('Корректный ответ сложения в 10 системе 41 + 19 = 60', () => {
    cy.get('#system').select('10');
    cy.get('#input1').clear().type('41');
    cy.get('#operationSelector').select('Сложить');
    cy.get('#input2').clear().type('19');
    cy.get('#calcButton').click();

    cy.get('#input3').should('have.value', '60');
  });

  it('Корректный ответ вычитания в 10 системе 92 - 93 = -1', () => {
    cy.get('#system').select('10');
    cy.get('#input1').clear().type('92');
    cy.get('#operationSelector').select('Вычесть');
    cy.get('#input2').clear().type('93');
    cy.get('#calcButton').click();

    cy.get('#input3').should('have.value', '-1');
  });

  it('Корректный ответ умножения в 10 системе 52 * 54 = 2808', () => {
    cy.get('#system').select('10');
    cy.get('#input1').clear().type('52');
    cy.get('#operationSelector').select('Умножить');
    cy.get('#input2').clear().type('54');
    cy.get('#calcButton').click();

    cy.get('#input3').should('have.value', '2808');
  });

  it('Корректный ответ деления в 10 системе 74 : 2 = 37', () => {
    cy.get('#system').select('10');
    cy.get('#input1').clear().type('74');
    cy.get('#operationSelector').select('Поделить');
    cy.get('#input2').clear().type('2');
    cy.get('#calcButton').click();

    cy.get('#input3').should('have.value', '37');
  });

  it('Корректный ответ сложения в 2 системе 111 + 10 = 1001', () => {
    cy.get('#system').select('2');
    cy.get('#input1').clear().type('111');
    cy.get('#operationSelector').select('Сложить');
    cy.get('#input2').clear().type('10');
    cy.get('#calcButton').click();

    cy.get('#input3').should('have.value', '1001');
  });

  it('Корректный ответ вычитания в 2 системе 100 - 1 = 11', () => {
    cy.get('#system').select('2');
    cy.get('#input1').clear().type('100');
    cy.get('#operationSelector').select('Вычесть');
    cy.get('#input2').clear().type('1');
    cy.get('#calcButton').click();

    cy.get('#input3').should('have.value', '11');
  });

  it('Корректный ответ умножения в 2 системе 111 * 111 = 110001', () => {
    cy.get('#system').select('2');
    cy.get('#input1').clear().type('111');
    cy.get('#operationSelector').select('Умножить');
    cy.get('#input2').clear().type('111');
    cy.get('#calcButton').click();

    cy.get('#input3').should('have.value', '110001');
  });

  it('Корректный ответ деления в 2 системе 100 : 10 = 10', () => {
    cy.get('#system').select('2');
    cy.get('#input1').clear().type('100');
    cy.get('#operationSelector').select('Поделить');
    cy.get('#input2').clear().type('10');
    cy.get('#calcButton').click();

    cy.get('#input3').should('have.value', '10');
  });

  it('Корректный ответ сложения в 8 системе 7 + 7 = 16', () => {
    cy.get('#system').select('8');
    cy.get('#input1').clear().type('7');
    cy.get('#operationSelector').select('Сложить');
    cy.get('#input2').clear().type('7');
    cy.get('#calcButton').click();

    cy.get('#input3').should('have.value', '16');
  });

  it('Корректный ответ вычитания в 8 системе 77 - 707 = -610', () => {
    cy.get('#system').select('8');
    cy.get('#input1').clear().type('77');
    cy.get('#operationSelector').select('Вычесть');
    cy.get('#input2').clear().type('707');
    cy.get('#calcButton').click();

    cy.get('#input3').should('have.value', '-610');
  });

  it('Корректный ответ умножения в 8 системе 77 * 7 = 671', () => {
    cy.get('#system').select('8');
    cy.get('#input1').clear().type('77');
    cy.get('#operationSelector').select('Умножить');
    cy.get('#input2').clear().type('7');
    cy.get('#calcButton').click();

    cy.get('#input3').should('have.value', '671');
  });

  it('Корректный ответ деления в 8 системе 63 : 7 = 1.35', () => {
    cy.get('#system').select('8');
    cy.get('#input1').clear().type('63');
    cy.get('#operationSelector').select('Поделить');
    cy.get('#input2').clear().type('7');
    cy.get('#calcButton').click();

    cy.get('#input3').should('have.value', '7.22222222222222222');
  });

  it('Корректный ответ сложения в 16 системе 12A + 7 = 131', () => {
    cy.get('#system').select('16');
    cy.get('#input1').clear().type('12A');
    cy.get('#operationSelector').select('Сложить');
    cy.get('#input2').clear().type('7');
    cy.get('#calcButton').click();

    cy.get('#input3').should('have.value', '131');
  });

  it('Корректный ответ вычитания в 16 системе 12A - 7 = 123', () => {
    cy.get('#system').select('16');
    cy.get('#input1').clear().type('12A');
    cy.get('#operationSelector').select('Вычесть');
    cy.get('#input2').clear().type('7');
    cy.get('#calcButton').click();

    cy.get('#input3').should('have.value', '123');
  });

  it('Корректный ответ умножения в 16 системе 12A * 76 = 826', () => {
    cy.get('#system').select('16');
    cy.get('#input1').clear().type('12A');
    cy.get('#operationSelector').select('Умножить');
    cy.get('#input2').clear().type('7');
    cy.get('#calcButton').click();

    cy.get('#input3').should('have.value', '826');
  });

  it('Корректный ответ деления в 16 системе 12A : 7 = 2', () => {
    cy.get('#system').select('16');
    cy.get('#input1').clear().type('12A');
    cy.get('#operationSelector').select('Поделить');
    cy.get('#input2').clear().type('7');
    cy.get('#calcButton').click();

    cy.get('#input3').should('have.value', '2A.924924924924');
  });

  it('Пустая строка при вводе букв и знаков в ввод 1', () => {
    cy.get('#input1').clear().type('AF+-/*ййы');

    cy.get('#input1').should('have.value', '');
  });

  it('Пустая строка при вводе букв в ввод 2', () => {
    cy.get('#input2').clear().type('AF+-/*ййы');

    cy.get('#input2').should('have.value', '');
  });

  it('Проверка в 2 системе, что можно ввести только 1 и 0 в ввод 1', () => {
    cy.get('#system').select('2');
    cy.get('#input1').clear().type('1023FA+-/*');

    cy.get('#input1').should('have.value', '10');
  });

  it('Проверка в 2 системе, что можно ввести только 1 и 0 в ввод 2', () => {
    cy.get('#system').select('2');
    cy.get('#input2').clear().type('1023FA+-/*');

    cy.get('#input2').should('have.value', '10');
  });

  it('Проверка в 8 системе, что можно ввести только числа от 0 до 7 в ввод 1', () => {
    cy.get('#system').select('8');
    cy.get('#input1').clear().type('1023456789FA+-/*');

    cy.get('#input1').should('have.value', '10234567');
  });

  it('Проверка в 8 системе, что можно ввести только числа от 0 до 7 в ввод 2', () => {
    cy.get('#system').select('8');
    cy.get('#input2').clear().type('1023456789FA+-/*');

    cy.get('#input2').should('have.value', '10234567');
  });

  it('Проверка в 16 системе, что можно ввести только числа от 0 до 9 и буквы A, B, C, D, E, F в ввод 1', () => {
    cy.get('#system').select('16');
    cy.get('#input1').clear().type('1023456789ABCDEFGT+-/*');

    cy.get('#input1').should('have.value', '1023456789ABCDEF');
  });

  it('Проверка в 16 системе, что можно ввести только числа от 0 до 9 и буквы A, B, C, D, E, F в ввод 2', () => {
    cy.get('#system').select('16');
    cy.get('#input1').clear().type('1023456789ABCDEFGT+-/*');

    cy.get('#input1').should('have.value', '1023456789ABCDEF');
  });

  it('Невозможно ввести 0 во второй ввод при операции деления', () => {
    cy.get('#system').select('10');
    cy.get('#operationSelector').select('Поделить');
    cy.get('#input2').clear().type('0');

    cy.get('#input2').should('have.value', '');
  });

  it('Зелёный фон при ответе положительном числе (1 + 1 = 1)', () => {
    cy.get('#system').select('10');
    cy.get('#input1').clear().type('1');
    cy.get('#operationSelector').select('Сложить');
    cy.get('#input2').clear().type('1');
    cy.get('#calcButton').click();

    cy.get('#input3').should('have.css', 'background-color', 'rgb(0, 128, 0)');
  });

  it('Чёрный фон при ответе ноль (0 + 0 = 0)', () => {
    cy.get('#system').select('10');
    cy.get('#input1').clear().type('0');
    cy.get('#operationSelector').select('Сложить');
    cy.get('#input2').clear().type('0');
    cy.get('#calcButton').click();

    cy.get('#input3').should('have.css', 'background-color', 'rgb(0, 0, 0)');
  });

  it('Красный фон при отрицательном ответе (1 - 5 = -4)', () => {
    cy.get('#system').select('10');
    cy.get('#input1').clear().type('1');
    cy.get('#operationSelector').select('Вычесть');
    cy.get('#input2').clear().type('5');
    cy.get('#calcButton').click();

    cy.get('#input3').should('have.css', 'background-color', 'rgb(255, 0, 0)');
  });
})
