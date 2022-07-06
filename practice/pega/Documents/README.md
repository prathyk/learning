## Table of Contents

* [Basic Information](#basic-information)
* [Installation Instructions](#installation-instructions)
* [Updating to a New Version of the Application](#updating-to-a-new-version-of-the-application)
* [Basic Usage](#basic-usage)
* [Custom Configuration](#custom-configuration)
* [Application Structure](#application-structure)

## Basic Information

The Pega React Starter Kit shows how to construct an React application to utilize the Pega-APIs with layout and field information.

## Installation Instructions

1.  Get Pega React Starter Pack zip file from Pega Exchange
2.  Unzip the PegaReactStarterPack813.zip, it should create PegaReactStarterPack813 directory that contains the following sub-directories: 
      CableConnectApp, Documents and ReactApp
3.  Using a terminal, cd into ./PegaReactStarterPack813.
4.  Install Cable Connect sample application by following the instruction in ./CableConnectSampleApp.pdf
5.  Make sure that your application server supports CORS.
6.  Install Yarn. Installation link [here](https://yarnpkg.com/lang/en/docs/install/#mac-stable).
7.  Using a terminal, cd into ./ReactApp directory and unzip PegaReact.zip.
8.  Using a terminal, cd into ./pegaapp-react
9.  Run the following commands:
    * `yarn install`
    * `yarn start`
10.  This should open your browser to http://localhost:3000, which is where the application will be served.

## Updating to a New Version of the Application

Run `yarn install` to ensure the latest dependencies are installed, then use `yarn start`.

## Basic Usage

By default, the application will be pointing to your local server http://localhost:1080/prweb.
To login to Cable Connect sample application, you can use any of the following credentials:

* rep.cableco, password: pega - case worker
* tech.cableco, password: pega – case worker
* manager.cableco, password: pega – case manager 
* admin.cableco, password: pega – developer/admin

Once logged in, you can create cases from the CaseType list, open WorkObjects from the WorkList, and perform end-to-end flows, based off the data returned from the API.

## Custom Configuration

If you want to point the application to a different server follow these steps:

* Open `PegaApp/src/_services/endpoints.js` and modify the BASEURL field to your desired system.
* Ensure that your desired application includes the ruleset Pega-API:08-01 (or higher).
* Ensure that your access group includes the PegaRULES:PegaAPI role.
* Check your application server CORS configuration if your system is not working correctly.

## Application Structure

If you are familiar with React / Redux, then many of the components and classes will be straightfoward.

```
PegaApp/
  README.md
  node_modules/
  package.json
  public/
  src/
    _actions/
    _components/
    _constants/
    _helpers/
    _reducers/
    _services/
    _styles/
    AppHeader/
    Dashboard/
    DashboardWidget/
    LoginPage/
    PegaApp/
    PegaForm/
    Workarea/
    Worklist/
    WorkObject/
```

Some of the most important directories and files are highlighted below:

## `_actions/`

These files contain action creators, used to dispatch actions via Redux.

There are separate files for actions related to:

* Alerts
* Assignments
* Cases
* Errors
* Users
* Workqueues

## `_reducers/`

Redux reducers.
Used to update state in the store after actions are dispatched.

There are separate reducers for:

* Alerts
* Assignments
* Cases
* Errors
* Users
* Workqueues

## `_services/`

Functions used to issue AJAX requests and manage responses.
All of the included methods use the Axios library for Promise-based requests.

There are separate service files for:

* Assignments
* Cases
* Data Pages
* Users

## `PegaForm/PegaForm.js`

This is a React component used to generate forms for assignments, views, and pages based on data returned from the Pega API.
Form generation for assignments, views, and pages are all based on a nested UI data structure returned from the API.

* Views / pages contain groups.
* Each element of a Group array can contain a view, layout, field, or paragraph.
* Layouts determine the UI structure of the form. Supported layout groupFormats are:
  * Stacked
  * Grid
  * Repeating Dynamic Layout
  * Inline middle
  * Inline grid double
  * Inline grid double (70 30)
  * Inline grid double (30 70)
* Fields contain information about the property, including reference, current value, outstanding validation messages, and attached actions.
* Supported fields:
  * pxTextInput
  * pxDropdown
  * pxCheckbox
  * pxTextArea
  * pxURL
  * repeating
  * tableLayout
  * pxEmail
  * pxDateTime
  * pxInteger
  * pxPhone
  * pxDisplayText
  * pxHidden
  * pxButton
  * label
  * pxLink
  * pxIcon
  * pxRadioButtons
  * pxCurrency
  * pxAutoComplete
* Supported actions:
  * setValue
  * postValue
  * refresh
  * takeAction
  * runScript

PageGroups and PageLists are supported.

When changing values on the form (checking a checkbox, typing into an input, etc...) the changes in value are reflected in state.values for PegaForm.
When doing a POST to submit an assignment or refresh fields, the state.values object is translated into a nested representation of the data based on page structure, and sent to the server.

## `_helpers/ReferenceHelper.js`

* Class to handle translating Pega's fully qualified property paths to a nested Object structure, and vice versa.
* Also some utility methods for:
  * Handling initial PegaForm state given View from API
  * Finding correct PageGroup/List based on property reference
  * Getting blank entry for PageGroup/List when adding new element
* When posting data to the server via API, it must be nested.
* When retrieving field information from the server, the property paths are flat and qualified.

## `_components/DataPageDropdown.js`

This is an example of a self-contained React component that has some Pega-API-specific logic.
In this case, the DataPageDropdown creates a dropdown form element that sources its options from a DataPage.

# Other Resources

This project was bootstrapped with [Create React App](https://github.com/facebookincubator/create-react-app).
