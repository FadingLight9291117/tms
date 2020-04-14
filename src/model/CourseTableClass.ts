// course table 中数据的数据类型
export default class CourseColumn {
    week: string;
    one: object | undefined;
    two: object | undefined;
    three: object | undefined;
    four: object | undefined;
    five: object | undefined;
    six: object | undefined;

    constructor(week: string) {
        this.week = week;
    }

    setOtherPrams(data: any) {
        switch (data.time as string) {
            case "one":
                this.one = {
                    id: data.id as number,
                    name: data.name as string
                };
                break;
            case "two":
                this.two = {
                    id: data.id as number,
                    name: data.name as string
                };
                break;
            case "three":
                this.three = {
                    id: data.id as number,
                    name: data.name as string
                };
                break;
            case  "four":
                this.four = {
                    id: data.id as number,
                    name: data.name as string
                };
                break;
            case "five":
                this.five = {
                    id: data.id as number,
                    name: data.name as string
                };
                break;
            case "six":
                this.six = {
                    id: data.id as number,
                    name: data.name as string
                };
                break;
        }
    }
}