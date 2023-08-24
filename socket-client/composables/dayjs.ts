import dayjs from "dayjs";

export const $dayjs = (date?: dayjs.ConfigType): dayjs.Dayjs => {
	return dayjs(date);
};
