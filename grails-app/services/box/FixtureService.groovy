package box

import grails.gorm.transactions.Transactional

@Transactional
class FixtureService {

    def createTestData() {
        
        def cate1 = new BookCategory([
            title: "Social",
            code: "ss1",
            description: "for study social media",
            borrwingDays: 10,
            findRatePerDay: 20
        ]).save()

        def cate2 = new BookCategory([
            title: "Sci",
            code: "sc1",
            description: "for study scient",
            borrwingDays: 5,
            findRatePerDay: 30
        ]).save()

        def author1 = new Author([
            titleTH: "เจเจ",
            titleEng: "JJ",
            age: 100
        ]).save()

        def author2 = new Author([
            titleTH: "ท่อ",
            titleEng: "Tor",
            age: 30
        ]).save()

        def book1 = new Book([
            titleTH: "ทดสอบ1",
            titleEng: "test1",
            author: "นายปูน",
            price: 3000,
            since: "2018-01-20",
            isRead: true,
            category: cate1
        ]).save()

        def bookAuthor1 = new BookAuthor([
            author: author1,
            book: book1
        ]).save()
    
        def book2 = new Book([
            titleTH: "ทดสอบ2",
            titleEng: "test2",
            author: "นายท่อ",
            price: 5000,
            since: "2019-01-20",
            isRead: false,
            category: cate2
        ]).save()

        new BookAuthor([
            author: author2,
            book: book2
        ]).save()

        def book3 = new Book([
            titleTH: "ทดสอบ3",
            titleEng: "Test3",
            author: "นายปูน",
            price: 500,
            since: "2018-02-20",
            isRead: false,
            category: cate1
        ]).save()

        new BookAuthor([
            author: author1,
            book: book3
        ]).save()

        new BookAuthor([
            author: author2,
            book: book3
        ]).save()

        new Book([
            titleTH: "ทดสอบ4",
            titleEng: "test4",
            author: "นายธฤต",
            price: 3000,
            since: "2019-02-20",
            isRead: true,
            category: cate2
        ]).save()

        new Book([
            titleTH: "กระจอก",
            titleEng: "kak",
            author: "นายเจเจ",
            price: 100,
            since: "2018-02-20",
            isRead: true,
            category: cate1
        ]).save()
        //1
        def teacher1 = new Teacher([
            teacherUser: 'teacher',
            teacherPassword: 'teacher',
            teacherTitle: 'นางสาว',
            teacherName: 'ซูวัยบะห์',
            teacherSname: 'วานิ',
            teacherAddress: 'นราธิวาส',
            teacherTel: '0820283604'
        ]).save()

        def teacher2 = new Teacher([
            teacherUser: 'teacher1',
            teacherPassword: 'teacher1',
            teacherTitle: 'นาย',
            teacherName: 'รอซดี',
            teacherSname: 'มะแอเคียน',
            teacherAddress: 'สตูล',
            teacherTel: '0820283604'
        ]).save()
        
        def teacher3 = new Teacher([
            teacherUser: 'teacher2',
            teacherPassword: 'teacher2',
            teacherTitle: 'นางสาว',
            teacherName: 'ฟาติมะห์',
            teacherSname: 'สะอะ',
            teacherAddress: 'นราธิวาส',
            teacherTel: '0631231456'
        ]).save()
        
        def teacher4 = new Teacher([
            teacherUser: 'teacher3',
            teacherPassword: 'teacher3',
            teacherTitle: 'นางสาว',
            teacherName: 'อัสมา',
            teacherSname: 'ปังมาก',
            teacherAddress: 'นราธิวาส',
            teacherTel: '0631231456'
        ]).save()
        
        def teacher5 = new Teacher([
            teacherUser: 'teacher3',
            teacherPassword: 'teacher3',
            teacherTitle: 'นางสาว',
            teacherName: 'อัสมา',
            teacherSname: 'ปังมากมาก',
            teacherAddress: 'นราธิวาส',
            teacherTel: '0631231456'
        ]).save()
        
        def parent1 = new Parent([
            parentUser: 'parent028',
            parentPassword: 'parent028',
            parentTitle: 'นาย',
            parentName: 'อะดินัส',
            parentSName: 'สบูบก',
            parentTel: '086485806',
            parentAddress: 'สงขลา',
            teacher:   teacher1
        ]).save()

        def parent2 = new Parent([
            parentUser: 'parent01',
            parentPassword: 'parent01',
            parentTitle: 'นาย',
            parentName: 'อัสรอน',
            parentSName: 'บินลาเต๊ะ',
            parentTel: '086485806',
            parentAddress: 'สงขลา',
            teacher: teacher1
        ]).save()

        def parent3 = new Parent([
            parentUser: 'parent02',
            parentPassword: 'parent02',
            parentTitle: 'นาย',
            parentName: 'อิสมาแอน',
            parentSName: 'ดาหมาด',
            parentTel: '086485806',
            parentAddress: 'สตูล ม.4 ',
            teacher: teacher1
        ]).save()

        def parent4 = new Parent([
            parentUser: 'parent03',
            parentPassword: 'parent03',
            parentTitle: 'นาย',
            parentName: 'รอซดี',
            parentSName: 'มะแอเคียน',
            parentTel: '086485806',
            parentAddress: ' 163 ม.2 ต.บ้านย่านซื่อ อ.ควนโดน จ.สตูล  ',
            teacher: teacher1
        ]).save()

        def parent5 = new Parent([
            parentUser: 'parent04',
            parentPassword: 'parent04',
            parentTitle: 'นาย',
            parentName: 'วิทยา',
            parentSName: 'อาดำ',
            parentTel: '0887995103',
            parentAddress: ' 163 ม.3 ต.เกตรี อ.เมือง จ.สตูล  ',
            teacher: teacher2
        ]).save()
        
        def classroom1 = new Classroom([
            className: 'ป.1',
            teacher: teacher1
        ]).save()
        
        def classroom2 = new Classroom([
            className: 'ป.2',
            teacher: teacher1
        ]).save()
        
        def classroom3 = new Classroom([
            className: 'ป.3',
            teacher: teacher1
        ]).save()
        
        def classroom4 = new Classroom([
            className: 'ป.4',
            teacher: teacher1
        ]).save()
        
        def classroom5 = new Classroom([
            className: 'ป.5',
            teacher: teacher2
        ]).save()
        
        def classroom6 = new Classroom([
            className: 'ป.6',
            teacher: teacher1
        ]).save()

        def student1 = new Student([
            studentTitle: 'เด็กหญิง',
            studentName: 'ขนิษฐา',
            studentSname: 'สบูบก',
            studentNickname: 'หนิง',
            studentSex: 'หญิง',
            classroom: classroom1,
            parent: parent1
        ]).save()

        def student2 = new Student([
            studentTitle: 'เด็กหญิง',
            studentName: 'อัสมา',
            studentSname: 'บินลาเต๊ะ',
            studentNickname: 'มา',
            studentSex: 'หญิง',
            classroom: classroom1,
            parent: parent2
        ]).save()

        def student3 = new Student([
            studentTitle: 'เด็กหญิง',
            studentName: 'อะมีต้า',
            studentSname: 'มะแอเคียน',
            studentNickname: 'มีต้า',
            studentSex: 'หญิง',
            classroom: classroom1,
            parent: parent4
        ]).save()

        def student4 = new Student([
            studentTitle: 'เด็กชาย',
            studentName: 'อัสมี',
            studentSname: 'บินลาเต๊ะ',
            studentNickname: 'อัสมี',
            studentSex: 'ชาย',
            classroom: classroom1,
            parent: parent2
        ]).save()

        def student5 = new Student([
            studentTitle: 'เด็กชาย',
            studentName: 'อัฟฟาน',
            studentSname: 'ดาหมาด',
            studentNickname: 'อัฟฟาน',
            studentSex: 'ชาย',
            classroom: classroom2,
            parent: parent3
        ]).save()

    }
}

