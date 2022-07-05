require("chromedriver"); 
const {Builder, By, Key, until} = require('selenium-webdriver');

const assert = require('assert')
const token = process.env;

console.log(token)

function testApp() {
  
}

describe('testApp', function() {
  this.timeout(30000)
  let driver
  let vars
  beforeEach(async function() {
    driver = await new Builder().forBrowser('chrome').build()
    vars = {}
  })
  afterEach(async function() {
    await driver.quit();
  })
  it('Login', async function() {
    await driver.get("https://google.com/")
    // await driver.manage().window().setRect({ width: 1936, height: 1048 })
    // await driver.findElement(By.id("mat-input-0")).click()
    // await driver.findElement(By.id("mat-input-0")).sendKeys("directeur@ensup.eu")
    // await driver.findElement(By.id("mat-input-1")).sendKeys("directeur")
    // await new Promise(r => setTimeout(r, 2000));
    //
    // await driver.findElement(By.css(".mat-form-field:nth-child(2)")).click()
    // await driver.findElement(By.css(".btn-cnx")).click()
    await new Promise(r => setTimeout(r, 2000));

  })
})
