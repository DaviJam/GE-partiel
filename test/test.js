require("chromedriver"); 

const {Builder, By, Key, until} = require('selenium-webdriver');

const assert = require('assert')


describe('ge_partiel', function() {
  this.timeout(120000)
  let driver
  let vars
  beforeEach(async function() {
    driver = await new Builder().forBrowser('chrome').build()
    vars = {}
  })
  afterEach(async function() {
    await driver.quit();
  })
  it('ge_partiel', async function() {
    await driver.get("http://13.41.191.244/")
    await driver.manage().window().setRect({ width: 1552, height: 832 })
    await new Promise(r => setTimeout(r, 5000));

    await driver.findElement(By.id("input-login-email")).click()
    await driver.findElement(By.id("input-login-email")).sendKeys("directeur@ensup.eu")
    await driver.findElement(By.id("input-login-pwd")).click()
    await driver.findElement(By.id("input-login-pwd")).sendKeys("directeur")
    await driver.findElement(By.css(".mat-card > .ng-touched")).click()

    await new Promise(r => setTimeout(r, 2000));
    await driver.findElement(By.id("btn-input-submit")).click()
    await new Promise(r => setTimeout(r, 2000));

    await driver.findElement(By.id("btn-header-create-student")).click()
    await new Promise(r => setTimeout(r, 2000));

    await driver.findElement(By.id("btn-form-student-lastname")).click()
    await driver.findElement(By.id("btn-form-student-lastname")).sendKeys("test")
    await driver.findElement(By.id("btn-form-student-firstname")).click()
    await driver.findElement(By.id("btn-form-student-firstname")).sendKeys("test")
    await driver.findElement(By.id("btn-form-student-naissance")).click()
    await driver.findElement(By.id("btn-form-student-naissance")).sendKeys("07-07-2022")
    await driver.findElement(By.id("btn-form-student-email")).click()
    await driver.findElement(By.id("btn-form-student-email")).sendKeys("test@gmail.com")
    await driver.findElement(By.id("btn-form-student-adresse")).click()
    await driver.findElement(By.id("btn-form-student-adresse")).sendKeys("test rue")
    await driver.findElement(By.id("btn-form-student-telephone")).click()
    await driver.findElement(By.id("btn-form-student-telephone")).sendKeys("1234567890")
    await driver.findElement(By.css(".glass")).click()
    await driver.findElement(By.id("btn-form-submit-student")).click()
    await driver.findElement(By.id("yes-btn")).click()
    await new Promise(r => setTimeout(r, 3000));

    await driver.findElement(By.id("btn-header-show-student")).click()

    
    await new Promise(r => setTimeout(r, 2000));

    await driver.findElement(By.id("home-btn-visibility")).click()
    await new Promise(r => setTimeout(r, 2000));
    await driver.findElement(By.id("btn-header-show-student")).click()
    await new Promise(r => setTimeout(r, 2000));
    await driver.findElement(By.id("home-btn-edit")).click()
    await new Promise(r => setTimeout(r, 2000));
    await driver.findElement(By.id("btn-edit-input-lastname")).click()
    await driver.findElement(By.id("btn-edit-input-lastname")).sendKeys("test1")
    await driver.findElement(By.id("btn-edit-input-firstname")).click()
    await driver.findElement(By.id("btn-edit-input-firstname")).sendKeys("test1")
    await driver.findElement(By.id("btn-edit-submit-form")).click()
    await driver.findElement(By.id("yes-btn")).click()

    await new Promise(r => setTimeout(r, 2000));
    await driver.findElement(By.id("btn-header-show-student")).click()

    await new Promise(r => setTimeout(r, 2000));
    await driver.findElement(By.id("btn-header-logout")).click()
    await new Promise(r => setTimeout(r, 2000));
    await driver.findElement(By.id("yes-btn")).click()
    await new Promise(r => setTimeout(r, 2000));

  })
})
