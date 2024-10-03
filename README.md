# SUReview

## Project Overview

SUReview is a student-driven application designed to enhance communication between students and faculty at Sabancı University. The platform allows students to review and rate courses, providing valuable feedback to both their peers and professors. Our aim is to bridge the communication gap between students and faculty, enabling informed course selection and continuous improvement of teaching quality. Faculty members can also benefit by receiving constructive insights on how to improve their courses.

## Project Aims

- **Enhance Communication**: Create an open channel for constructive feedback between students and faculty.
- **Improve Course Selection**: Help students make better course registration decisions based on peer reviews and ratings.
- **Facilitate Continuous Improvement**: Provide faculty with actionable feedback to improve their teaching and course design.
- **Build a Community**: Foster a stronger sense of collaboration and understanding between students and professors through shared experiences.

## Main Project Targets

- **User Engagement**: Develop an intuitive UI/UX to encourage active participation from both students and faculty.
- **Data Integrity**: Implement moderation and verification processes to ensure that reviews and ratings are authentic and useful.
- **Privacy and Security**: Protect user privacy through strong data handling policies, with options for anonymity.
- **Scalability**: Design the backend architecture to accommodate increasing numbers of users and growing data volume.

## Backend Endpoints

Our backend infrastructure will handle a variety of data interactions and user transactions. Security and scalability are paramount. The key endpoints include:

- **User Authentication**: `register/`, `login/`, `logout/`
- **Course Management**: `courses/list`, `courses/add`, `courses/update`, `courses/delete`
- **Professor Management**: `professors/list`, `professors/add`, `professors/update`, `professors/delete`
- **Reviews and Ratings**: `reviews/add`, `reviews/list`, `reviews/update`, `reviews/delete`
- **Feedback for Improvement**: `feedback/add`, `feedback/list`
- **Analytics**: `analytics/course`, `analytics/professor`

## Mobile Application Screens

The mobile app interface will be designed with ease of use and high engagement in mind. Key screens include:

- **Login/Register**: User authentication gateway.
- **Home/Dashboard**: Display of featured courses, top-rated professors, and recent reviews.
- **Course List**: A searchable list of courses by department, professor, or rating.
- **Course Details**: In-depth course information, including descriptions, ratings, reviews, and professor details.
- **Professor Profiles**: Display of professor bios, their courses, ratings, and reviews.
- **Submit Review**: A form for users to write reviews and rate courses or professors.
- **User Profile**: Shows the user's details, submitted reviews, and saved courses or professors.
- **Settings**: Allows users to customize their experience, including privacy options like anonymity.

## Similar Applications and Users

Sabancı University currently offers a questionnaire called "Student Feedback on Teaching And Learning" through BAGEM at the end of each term. However, this form alone does not provide enough information for students about courses. SUReview aims to create an honest, dedicated platform where students can access peer reviews and make informed decisions based on the experiences of others.

While most users will be Sabancı students, faculty members will also benefit from the feedback provided. If successful, this platform could be adapted for use at other institutions due to its simplicity, user-friendly design, and powerful functionality.

## Authors

- [Tolga Tektunalı](https://github.com/Tolga745)
- [Kadir Yağız Ebil](https://github.com/YagizEbil)
- [Ege Yurtsever](https://github.com/evesfect)

## License

This project is licensed under the MIT License.
