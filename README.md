# Welcome to Travel Organizer!

[![License](https://img.shields.io/badge/license-MIT-blue.svg)](LICENSE)
![Android Version](https://img.shields.io/badge/Android-8.0%2B-green.svg)
[![Figma Storyboard](https://img.shields.io/badge/Figma-Storyboard-orange)](https://www.figma.com/file/NlP4hHGLwg8ub4sDrLd2Pi/Mobile-App-Development-Story-Board?type=design&node-id=0%3A1&mode=design&t=nsr8MFZZ2FURqWf0-1)
[![Privacy Policy](https://img.shields.io/badge/Privacy%20Policy-Read%20Now-blue)](https://github.com/GageAllenCarpenter/Android-Vacation-Scheduler/blob/main/PRIVACY-POLICY.txt)

Travel Organizer is a mobile application that allows users to plan and organize their vacations. The application helps users keep track of their vacations, including details like vacation title, accommodation, start date, end date, and associated excursions. Users can also set alerts for vacation start and end dates, share vacation details, and manage their excursions. The app is designed to be user-friendly and compatible with Android 8.0 and higher.
# Deployment

This application has been deployed to the Google Play Store here: [Vacation Scheduler](https://play.google.com/store/apps/details?id=edu.wgu.scheduler)

## CI/CD
An automated CI/CD pipeline was built utilizing Gitlab and Fastlane. More information on this can be viewed here: [Gitlab Repository](https://gitlab.com/gcarp12/android-vacation-scheduler) 

# Application Features

## Vacation Management

- Create, update, read, and delete vacations.
- Validate that vacations cannot be deleted if excursions are associated with them.

## Vacation Details

- Enter and view vacation details, including title, accommodation, start date, and end date.
- Edit vacation information with proper validation for date formatting and start-end date relationship.
- Set alerts for vacation start and end dates.

## Sharing Feature

- Share vacation details via e-mail, clipboard, or SMS.

## Excursion Management

- Display a list of excursions associated with each vacation.
- Add, update, read, and delete excursions.
- Validate that excursion dates are within the associated vacation period.

## Excursion Details

- Enter and view excursion details, including title and date.
- Edit excursion information with proper validation for date formatting.
- Set alerts for excursion dates.

# Android Version Deployed
The signed APK of the Travel Organizer application is deployed for Android version 8.0 (oreo) and higher
#  How To Operate
The application consists of 3 main screens. The home screen, the vacation list, and the excursion list are the three screens in the application. To get to these screens the user must click the only button on the home screen which will take the user to the vacation screen. The vacation screen is a list of all vacations and holds all functionalities pertaining to a vacation including the share feature. If the user would like to see the excursions associated with the particular vacation, the user can select anywhere on the vacations box besides the buttons that are labeled otherwise and it will direct the user to the excursion list screen which yields the excursions associated with that vacation. If the user would like to add either excursions or vacations they can do so with the plus sign that does not move on either excursion or vacation screens. To perform all other stated functionalities, the buttons, and text on the vacation items or excursion items lists can be interacted with to perform create, read, update, or delete operations on the vacation or excursion entities. Below you will find a link to a storyboard that displays the flow of the application further through the use of images.

<B> Story Board: </b> https://www.figma.com/file/NlP4hHGLwg8ub4sDrLd2Pi/Mobile-App-Development-Story-Board?type=design&node-id=0%3A1&mode=design&t=nsr8MFZZ2FURqWf0-1
