import '../FieldBase/FieldBase.es';
import '../Text/Text.es';
import './KeyValueRegister.soy.js';

import Component from 'metal-component';
import Soy from 'metal-soy';
import templates from './KeyValue.soy.js';
import {Config} from 'metal-state';
import {
	normalizeFieldName
} from 'dynamic-data-mapping-form-builder/js/components/LayoutProvider/util/fields.es';

/**
 * KeyValue.
 * @extends Component
 */

class KeyValue extends Component {
	willReceiveState(changes) {
		if (changes.keyword) {
			this.setState(
				{
					_keyword: changes.keyword.newVal
				}
			);
		}

		if (changes.value) {
			this.setState(
				{
					_value: changes.value.newVal
				}
			);
		}
	}

	_handleKeywordInputBlurred(event) {
		this.emit(
			'fieldBlurred',
			{
				fieldInstance: this,
				originalEvent: event,
				value: event.target.value
			}
		);
	}

	_handleKeywordInputChanged(event) {
		const {target} = event;
		let {value} = target;

		value = normalizeFieldName(value);

		target.value = value;

		this.setState(
			{
				generateKeyword: false,
				keyword: value
			},
			() => {
				this.emit(
					'fieldKeywordEdited',
					{
						fieldInstance: this,
						originalEvent: event,
						value
					}
				);
			}
		);
	}

	_handleValueInputBlurred({originalEvent, value}) {
		this.emit(
			'fieldBlurred',
			{
				fieldInstance: this,
				originalEvent,
				value
			}
		);
	}

	_handleValueInputEdited(event) {
		const {generateKeyword} = this;
		let {keyword} = this;
		const {originalEvent, value} = event;

		if (generateKeyword) {
			keyword = normalizeFieldName(value);
		}

		this.setState(
			{
				keyword,
				value
			},
			() => {
				this.emit(
					'fieldKeywordEdited',
					{
						fieldInstance: this,
						originalEvent,
						value: keyword
					}
				);
				this.emit(
					'fieldEdited',
					{
						fieldInstance: this,
						originalEvent,
						value
					}
				);
			}
		);
	}

	_handleValueInputFocused({originalEvent, value}) {
		this.emit(
			'fieldFocused',
			{
				fieldInstance: this,
				originalEvent,
				value
			}
		);
	}

	_internalKeywordFn() {
		const {keyword} = this;

		return keyword;
	}

	_internalValueFn() {
		const {value} = this;

		return value;
	}
}

KeyValue.STATE = {

	_keyword: Config.string().internal().valueFn('_internalKeywordFn'),

	_value: Config.string().internal().valueFn('_internalValueFn'),

	/**
	 * @default undefined
	 * @instance
	 * @memberof Text
	 * @type {?(string|undefined)}
	 */

	fieldName: Config.string(),

	/**
	 * @default false
	 * @instance
	 * @memberof KeyValue
	 * @type {?bool}
	 */

	generateKeyword: Config.bool().value(true),

	/**
	 * @default undefined
	 * @instance
	 * @memberof KeyValue
	 * @type {?(string|undefined)}
	 */

	id: Config.string(),

	/**
	 * @default undefined
	 * @instance
	 * @memberof KeyValue
	 * @type {?(string|undefined)}
	*/

	keyword: Config.string(),

	/**
	 * @default false
	 * @instance
	 * @memberof KeyValue
	 * @type {?boolean}
	*/

	keywordReadOnly: Config.bool().value(false),

	/**
	 * @default undefined
	 * @instance
	 * @memberof KeyValue
	 * @type {?(string|undefined)}
	 */

	label: Config.string(),

	/**
	 * @default undefined
	 * @instance
	 * @memberof Select
	 * @type {?string}
	 */

	predefinedValue: Config.string().value('Option 1'),

	/**
	 * @default false
	 * @instance
	 * @memberof KeyValue
	 * @type {?bool}
	 */

	readOnly: Config.bool().value(false),

	/**
	 * @default undefined
	 * @instance
	 * @memberof FieldBase
	 * @type {?(bool|undefined)}
	 */

	repeatable: Config.bool(),

	/**
	 * @default false
	 * @instance
	 * @memberof KeyValue
	 * @type {?bool}
	 */

	required: Config.bool().value(false),

	/**
	 * @default true
	 * @instance
	 * @memberof KeyValue
	 * @type {?bool}
	 */

	showLabel: Config.bool().value(true),

	/**
	 * @default undefined
	 * @instance
	 * @memberof KeyValue
	 * @type {?(string|undefined)}
	 */

	spritemap: Config.string(),

	/**
	 * @default undefined
	 * @instance
	 * @memberof FieldBase
	 * @type {?(string|undefined)}
	 */

	tip: Config.string(),

	/**
	 * @default undefined
	 * @instance
	 * @memberof Text
	 * @type {?(string|undefined)}
	 */

	type: Config.string().value('key-value'),

	/**
	 * @default undefined
	 * @instance
	 * @memberof KeyValue
	 * @type {?(bool)}
	 */

	value: Config.string()
};

Soy.register(KeyValue, templates);

export default KeyValue;