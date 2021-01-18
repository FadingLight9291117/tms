// course table 中数据的数据类型
export default class CourseColumn {
    week: string;
    one: any;
    two: any;
    three: any;
    four: any;
    five: any;
    six: any;

    constructor(week: string) {
        this.week = week;
        this.setOtherParamsNull();
    }

    setOtherParamsNull() {
        this.two = null;
        this.one = null;
        this.three = null;
        this.four = null;
        this.five = null;
        this.six = null;
    }

    setOtherParams(data: any) {
        switch (data.time as string) {
            case "one":
                this.one = {
                    id: data.id as number,
                    className: data.className as string
                };
                break;
            case "two":
                this.two = {
                    id: data.id as number,
                    className: data.className as string
                };
                break;
            case "three":
                this.three = {
                    id: data.id as number,
                    className: data.className as string
                };
                break;
            case  "four":
                this.four = {
                    id: data.id as number,
                    className: data.className as string
                };
                break;
            case "five":
                this.five = {
                    id: data.id as number,
                    className: data.className as string
                };
                break;
            case "six":
                this.six = {
                    id: data.id as number,
                    className: data.className as string
                };
                break;
        }
    }
}